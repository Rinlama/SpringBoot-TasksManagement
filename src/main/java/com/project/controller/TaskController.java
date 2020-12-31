package com.project.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CountType;
import com.project.model.Task;
import com.project.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin("*")
public class TaskController {
	
	private TaskService taskService;
	
	
	@GetMapping("/task/vData/percentcounttype")
	public List<CountType> getCountTaskByType(){
		return taskService.getGroupByType();
	}
	
	@GetMapping("/task")
	public List<Task> getTask(){
		return taskService.getTasks();
	}
	
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable Long id){
		return taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Task not Found"));
	}
	
	@PostMapping("/task")
	public Task addRestaurant(@RequestBody @Valid Task task){
		return taskService.save(task);
	}
	
	
	@PutMapping("/task/{id}")
	public ResponseEntity<?> updateTask(@RequestBody Task taskPara, @PathVariable Long id) {
		 if(taskService.existById(id) ){
			    Task task= taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Task not Found"));
				task.setTitle(taskPara.getTitle());
				task.setDescription(taskPara.getDescription());
				task.setDueDate(taskPara.getDueDate());
				task.setType(taskPara.getType());
				
				taskService.save(task);
			    return ResponseEntity.ok().body(task);
			}else{
				HashMap<String, String> message=new HashMap<String,String>();
				message.put("message", id + "Task not found or Id not matched");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
			} 
	  }
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> removeTask(@PathVariable(value = "id") Long id){
		   if(taskService.existById(id)){
			  taskService.delete(id);
			  Map<String, Object> body = new LinkedHashMap<>();
			  body.put("timestamp", LocalDateTime.now());
			  body.put("message", "Remove location");
			  return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
			} else {
				HashMap<String, String> message=new HashMap<String,String>();
				message.put("message", id + "Task not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
			}
	}


}
