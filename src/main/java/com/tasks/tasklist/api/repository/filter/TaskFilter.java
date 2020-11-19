package com.tasks.tasklist.api.repository.filter;

import com.tasks.tasklist.api.model.enums.Status;

public class TaskFilter {

	private String titulo;
	private Integer status;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
