package com.chinamobile.cmss;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

/**
 * @author 杜奎
 * @date 2017/11/5
 * @version v0.0.1
 */

@SpringBootApplication
@MapperScan(value="com.chinamobile.cmss.mapper")
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class AlertsForwarderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertsForwarderApplication.class, args);
	}
}
