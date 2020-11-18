package com.tasks.tasklist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasks.tasklist.api.model.Task;
import com.tasks.tasklist.api.repository.query.TaskRepositoryQuery;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryQuery {

}
