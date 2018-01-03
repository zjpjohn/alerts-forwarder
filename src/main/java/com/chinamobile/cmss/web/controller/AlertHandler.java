package com.chinamobile.cmss.web.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chinamobile.cmss.domain.Alert;
import com.chinamobile.cmss.domain.Client;
import com.chinamobile.cmss.domain.Echo;
import com.chinamobile.cmss.domain.WWWAlert;
import com.chinamobile.cmss.service.WWWAlertService;
import com.chinamobile.cmss.utils.AppUtils;

/**
 * @author 中国移动苏州研发中心-IT支撑产品部-杜奎
 * @date 2017/11/08
 * @version v0.0.1
 * @description AlertHandler类是一个核心控制器类，它负责接收Prometheus传送来的信息，
 *              接着对其进行封装为标准的告警格式，最后将告警信息加入redis消息队列，具体的告
 *              警发送由Client后台监听线程完成，即做到了封装与发送异步进行。
 *              
 *              Redis在此扮演两种角色：
 *              	① 缓存
 *			    	② 消息中间件(RabbitMQ/RocketMQ/Kafka)
 */
@RestController
public class AlertHandler {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private WWWAlert wwwAlert;
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private WWWAlertService wwwAlertService;
	
	@Autowired
	private Client client;
	
	@RequestMapping(value = "/api/v1/alerts",method = RequestMethod.POST)
	@ResponseBody
	public Echo getAlerts(@RequestBody ArrayList<Alert> alerts) {
		
		logger.info(alerts.size() + " alert(s) is coming···");
		
		// 该接口每接受一次客户端请求，api.v1.alerts.invoked.count 就增加 1
		counterService.increment("api.v1.alerts.invoked.count");
		
		ListOperations<String,String> listOps = stringRedisTemplate.opsForList();
		ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
		
		// the key of list in Redis
		String cached_alerts = "cached_alerts";
		String queued_metrics = "queued_metrics";
		
		for(int i = 0; i < alerts.size(); i++) {
			
			Map<String,String> labels = alerts.get(i).getLabels();
			
			wwwAlert = populate(WWWAlert.class, labels);
			
			String metric = wwwAlert.getLocateInfo();
			
			/*
			 * Socket连接断开，此时AlertHadnler接收到的原生告警信息需要缓存到Redis队列中，
			 * 待Socket连接建立后，再由Client从Redis队列中取出数据一一发送。
			 */
			if(null == client.getSocket()) {
				logger.warn("socket connection is unnormal, cache alerts to Redis");
				
				listOps.leftPush(cached_alerts, wwwAlert.toString());
				
			/*
			 * Socket连接保持中，此时AlertHandler需要将封装后的告警信息添加到缓存和队列中。
			 * Redis缓存利用String数据结构，key=指标名，value=封装后的告警信息；
			 * Redis队列利用List数据结构，这也是Redis常见的应用场景之一；
			 */
			}else {
				if("".equals(valueOps.get(metric)) || null == valueOps.get(metric)) {
					logger.info("socket connection is normal,but there is no metric in Redis");
					
					wwwAlertService.insert(wwwAlert);    // 持久化到mysql数据库中
					valueOps.set(metric,wwwAlert.toString());
					listOps.leftPush(queued_metrics, metric);
				}else {
					logger.info("socket connection is normal,and the metric has been cached in Redis");
					
					wwwAlertService.update(wwwAlert);    // 更新mysql数据库中数据
					valueOps.set(metric,wwwAlert.toString());
					listOps.leftPush(queued_metrics, metric);
				}
			}
		}
		
		logger.info("one request has been handled completely");
		
		return new Echo("succes",LocalDateTime.now().toString());
	}
	
	/*
	 * 利用反射机制将Map中key-value键值儿对封装到WWWAlert实例中，同时调整WWWAlert实例中部分属性值；
	 * 这部分其实有一个比较简单的方法：利用apache-common-beanutils的静态工具类BenUtils.populate(bean,map)自动填充bean。
	 */
	public WWWAlert populate(Class<? extends WWWAlert> clazz, Map<String,String> properties) {
		
		WWWAlert alert = null;
		
		try {
			alert = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			
			Iterator<Entry<String,String>> it = properties.entrySet().iterator();
			
			while(it.hasNext()) {
				Entry<String,String> entry = it.next();
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				
				for(int i = 0; i < fields.length; i ++) {
					String fieldName = fields[i].getName();
					
					if(fieldName.equals(key)) {
						
						for(int j = 0; j < methods.length; j ++) {
							
							if(methods[j].getName().toLowerCase().equals("set"+fieldName.toLowerCase())) {
								
								if (fieldName.equals("EventTime")) {
									methods[j].invoke(alert, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
								}else {
									methods[j].invoke(alert, value);
								}
							}
						}
					}
				}
			}
		}catch(InstantiationException e1) {
			e1.printStackTrace();
		}catch(IllegalAccessException e2) {
			e2.printStackTrace();
		}catch(InvocationTargetException e3) {
			e3.printStackTrace();
		}
		
		String metrics = alert.getLocateInfo();
		alert.setAlarmUniqueId(AppUtils.generateUniqueId(metrics));
		
		return alert;
	}
}