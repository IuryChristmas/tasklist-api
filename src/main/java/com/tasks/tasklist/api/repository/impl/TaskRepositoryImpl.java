package com.tasks.tasklist.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.tasks.tasklist.api.model.Task;
import com.tasks.tasklist.api.repository.filter.TaskFilter;
import com.tasks.tasklist.api.repository.query.TaskRepositoryQuery;

public class TaskRepositoryImpl implements TaskRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Task> buscar(TaskFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
		
		Root<Task> root = criteria.from(Task.class);
		
		Predicate[] predicates = this.criarRestricoes(filter, builder, root);
		
		criteria.select(builder.construct(Task.class, root.get("id"), root.get("titulo"), root.get("status"),
				root.get("descricao"), root.get("dataCriacao"), root.get("dataConclusao")));
		criteria.where(predicates);
		
		TypedQuery<Task> query = manager.createQuery(criteria);
		this.adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, count(filter));
	}
	
	private Predicate[] criarRestricoes(TaskFilter filter, CriteriaBuilder builder, Root<Task> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getTitulo())) {
			predicates.add(builder.like(builder.lower(root.get("titulo")), "%" + filter.getTitulo().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Task> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long count(TaskFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Task> root = criteria.from(Task.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
}
