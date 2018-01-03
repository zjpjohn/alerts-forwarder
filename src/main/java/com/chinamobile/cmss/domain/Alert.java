package com.chinamobile.cmss.domain;

/**
 * @author 杜奎
 * @date 2017/11/09
 * @description Prometheus原生告警信息封装
 * @version v0.0.1
 */
import java.util.HashMap;

public class Alert {
	private HashMap<String,String> labels;
	private HashMap<String,String> annotations;
	private String startsAt;
	private String endsAt;
	private String generatorURL;
	
	public HashMap<String, String> getLabels() {
		return labels;
	}
	public void setLabels(HashMap<String, String> labels) {
		this.labels = labels;
	}
	public HashMap<String, String> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(HashMap<String, String> annotations) {
		this.annotations = annotations;
	}
	public String getStartsAt() {
		return startsAt;
	}
	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}
	public String getEndsAt() {
		return endsAt;
	}
	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
	}
	public String getGeneratorURL() {
		return generatorURL;
	}
	public void setGeneratorURL(String generatorURL) {
		this.generatorURL = generatorURL;
	}
}