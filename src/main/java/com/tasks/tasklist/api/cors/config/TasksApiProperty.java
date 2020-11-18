package com.tasks.tasklist.api.cors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tasks")
public class TasksApiProperty {

	private String originPermitida = "http://localhost:8000";
	
	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
}
