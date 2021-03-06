package com.tasks.tasklist.api.business;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasks.tasklist.api.model.Task;
import com.tasks.tasklist.api.model.enums.Status;
import com.tasks.tasklist.api.repository.TaskRepository;
import com.tasks.tasklist.api.repository.filter.TaskFilter;

@Service
public class TaskBusiness {

	@Autowired
	private TaskRepository repository;
	
	public Page<Task> buscar(TaskFilter filter, Pageable pageable) {
		Page<Task> tasks = repository.buscar(filter, pageable);
		
		return tasks;
	}

	@Transactional(rollbackFor = Exception.class)
	public Task salvar(Task task) {
		task.setDataCriacao(new Date());
		task.setDataConclusao(task.getStatus().equals(Status.PENDENTE) ? null : new Date());

		return repository.save(task);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void excluid(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Task atualizar(Task task) {		
		task.setDataAtualizacao(new Date());
		Date dataConclusao = task.getDataConclusao() != null ? task.getDataConclusao() : new Date();
		task.setDataConclusao(task.getStatus().equals(Status.PENDENTE) ? null : dataConclusao);

		return repository.save(task);
	}
	
	public Optional<Task> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Task concluirTask(Task task) {
		task.setDataAtualizacao(new Date());
		task.setDataConclusao(task.getStatus().equals(Status.PENDENTE) ? null : new Date());
		
		return repository.save(task);
	}
}
