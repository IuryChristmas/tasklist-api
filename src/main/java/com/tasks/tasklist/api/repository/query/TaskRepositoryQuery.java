package com.tasks.tasklist.api.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tasks.tasklist.api.model.Task;
import com.tasks.tasklist.api.repository.filter.TaskFilter;

public interface TaskRepositoryQuery {
	
	public Page<Task> buscar(TaskFilter filter, Pageable pageable);

}
