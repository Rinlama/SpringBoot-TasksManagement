package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dto.CountType;
import com.project.model.Task;
import com.project.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
	
	private TaskRepository taskRepository;

	@Transactional(readOnly = true)
	public List<Task> getTasks(){
		return taskRepository.getAllTask();
	}
	
	@Transactional
	public Task save(Task task) {
		return taskRepository.saveAndFlush(task);
	}
	@Transactional
	public Boolean existById(Long id) {
		return taskRepository.existsById(id);
	}
	@Transactional
	public void delete(Long id) {
		 taskRepository.deleteById(id);
	}

	public Optional<Task> getTaskById(Long id) {
		return taskRepository.findById(id);
	}
	
	public List<CountType> getGroupByType() {
		return taskRepository.getGroupByType();
	}

}
