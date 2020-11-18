package com.tasks.tasklist.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tasks.tasklist.api.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tasks")
public class Task {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	private String descricao;
		
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@Column(name="data_atualizacao")
	private Date dataAtualizacao;
	
	@Column(name="data_conclusao")
	private Date dataConclusao;
	
	public Task() {}
	
	public Task(Long id) {
		this.id = id;
	}

	public Task(Long id, String titulo, Status status, String descricao, Date dataCriacao, Date dataConclusao) {
		this.id = id;
		this.titulo = titulo;
		this.status = status;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
	}
	
	public Task(Long id, String titulo, Status status, String descricao, Date dataCriacao, Date dataAtualizacao,
			Date dataConclusao) {
		this.id = id;
		this.titulo = titulo;
		this.status = status;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.dataConclusao = dataConclusao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Long getId() {
		return id;
	}
	
}
