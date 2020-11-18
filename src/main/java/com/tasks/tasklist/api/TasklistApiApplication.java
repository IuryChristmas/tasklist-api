package com.tasks.tasklist.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tasks.tasklist.api.cors.config.TasksApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(TasksApiProperty.class)
public class TasklistApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklistApiApplication.class, args);
	}

}
