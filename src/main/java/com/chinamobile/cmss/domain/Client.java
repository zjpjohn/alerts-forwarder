package com.chinamobile.cmss.domain;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author 杜奎
 * @date 2017/11/10
 * @version v0.0.1
 * @description Client线程类负责TCP连接、TCP自动重连、TCP心跳消息发送、Prometheus告警信息发送；
 *              若Redis队列中告警信信息为空，说明没有告警需要发送，则进行心跳消息发送；
 *              若Redis队列中告警信息非空，说明有告警信息需要发送，则不尽兴心跳消息发送。
 *              
 *              Client类将在本程序启动的时候初始化为一个Bean，其实相当于一个TCP后台监听线程！
 */

@Component
public class Client {
	
	private final static Logger logger = LogManager.getLogger();
		
	private Socket socket;
	
	private OutputStream out;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/*
	 * 直接@Autowired自动装配会导致tcpProperties实例为null，从而抛出空指针异常。
	 * 但是为什么抛出异常呢？难道是Bug? 那是因为Client会先执行Client()构造函数创建
	 * Client实例，然后再装配tcpProperties字段，可是还没来得及装配该字段，其构造函数
	 * 却试图执行tcpProperties.getIp()实例方法了，空指针异常无疑···
	 * 
	 * TcpProperties类主要是为了避免将TCP之IP和PORT写死在程序中···
	 */
	private TcpProperties tcpProperties;
	
	@Autowired
	public Client(TcpProperties tcpProperties) {
		this.tcpProperties = tcpProperties;
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						if(null != socket) {
							try {
								ListOperations<String,String> listOps = stringRedisTemplate.opsForList();
								ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
								if(listOps.size("queued_metrics") > 0) {
									logger.info("send alerts normally");
									for(int i = 0; i < listOps.size("queued_metrics"); i ++) {
										out.write(valueOps.get(listOps.rightPop("queued_metrics")).getBytes());
										out.flush();
									}
								}else if(listOps.size("cached_alerts") > 0) {
									logger.info("send cached alerts which produced by socket exception");
									for(int j = 0; j < listOps.size("cached_alerts"); j ++) {
										out.write(listOps.rightPop("cached_alerts").getBytes());
										listOps.rightPop("queued_metrics");
										out.flush();
									}
								}else {
									TimeUnit.SECONDS.sleep(10);    // 设置心跳消息发送间隔为10s
									logger.info("Socket client connected···,send heartbeat");
									out.write("<heartbeat>\r\n".getBytes());
									out.flush();
								}
							}catch(IOException e) {
								logger.error("send heartbeat failure···,the socket connection may be closed or reset by Server");
								out.close();
								socket.close();
								socket = null;
								e.printStackTrace();
						    }
						}else {
							logger.info("Socket client re-connecting···,ip=" + tcpProperties.getIp() + ",port=" + tcpProperties.getPort());
							socket = new Socket(tcpProperties.getIp(),tcpProperties.getPort());    // TCP连接唯一，不会创建多个TCP连接
							socket.setKeepAlive(true);
							out = socket.getOutputStream();
							Thread.sleep(6000);    // 设置自动重连间隔时间为6s
						}
					}catch(IOException e) {
						logger.fatal("Socket client re-connect failed···");
						socket = null;
						e.printStackTrace();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public Socket getSocket() {
		return this.socket;
	}
	
	public OutputStream getOutputStream() {
		return this.out;
	}

	public TcpProperties getTcpProperties() {
		return tcpProperties;
	}

	public void setTcpProperties(TcpProperties tcpProperties) {
		this.tcpProperties = tcpProperties;
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}
}
