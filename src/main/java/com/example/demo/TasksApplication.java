package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.project.model.Task;
import com.project.service.TaskService;

import lombok.AllArgsConstructor;


@SpringBootApplication
@EntityScan("com.project")
@ComponentScan("com.project")
@EnableJpaRepositories("com.project")
@AllArgsConstructor
public class TasksApplication implements CommandLineRunner {
	
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Task task=Task.builder().title("Design the solution").description("Identify resources to be monitored.\n" + 
				"Define users and workflow\n" + 
				"Identify event sources by resource type.\n" + 
				"Define the relationship between resources and business systems.\n" + 
				"Identify tasks and URLs by resource type.\n" + 
				"Define the server configuration.").type("todo").dueDate(new Date()).build();
		
		Task taskAgain=Task.builder().title("Prepare for implementation").description("Identify the implementation team.\n" + 
				"Order the server hardware for production as well as test/quality assurance (QA).\n" + 
				"Order console machines.\n" + 
				"Order prerequisite software.\n" + 
				"Identify the test LPAR.\n" + 
				"Identify production LPARs.").type("done").dueDate(new Date()).build();
		
		
		taskService.save(task);
		taskService.save(taskAgain);
	
	}
	

}
