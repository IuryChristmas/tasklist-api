package com.tasks.tasklist.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasklist.api.business.TaskBusiness;
import com.tasks.tasklist.api.model.Task;
import com.tasks.tasklist.api.repository.filter.TaskFilter;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskBusiness business;
	
	@GetMapping
	public Page<Task> buscar(TaskFilter filter, Pageable pageable) {
		return business.buscar(filter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Task> task = business.buscarPorId(id);
		
		if(!task.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(task);
	}
	
	@PostMapping
	public ResponseEntity<Task> salvar(@RequestBody Task task) {
		
		return ResponseEntity.ok(business.salvar(task));
	}
	
	@PutMapping
	public ResponseEntity<Task> atualizar(@RequestBody Task task) {
		return ResponseEntity.ok(business.atualizar(task));
	}
	
	@PutMapping("/concluir")
	public ResponseEntity<Task> concluirTask(@RequestBody Task task) {
		return ResponseEntity.ok(business.concluirTask(task));
	}
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {
		business.excluid(id);
	}
}
