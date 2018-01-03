package com.chinamobile.cmss.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 杜奎
 * @date 2017/11/10
 * @version v0.0.1
 * @description TcpProperties类专门负责从以下属性源中加载自定义配置属性：
 *              	①命令行
 *              	②JVM系统属性
 *              	③操作系统环境变量
 *              	④application.properties、application.yaml
 *              ConfigurationProperties注解类声明TcpProperties类中的字段值需要从有效属性源中加载；
 *              Spring Boot会自动加载属性源中以tcp为前缀的属性值儿。
 *
 */

@Component
@ConfigurationProperties(prefix="tcp")
public class TcpProperties {
	private String ip;
	private int port;
	
	public TcpProperties() {
		
	}
	public TcpProperties(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return "current tcp server info >>> ip = " + this.ip + ", port = " + this.port;
	}
}