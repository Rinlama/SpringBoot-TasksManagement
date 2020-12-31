package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.dto.CountType;
import com.project.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>  {

	@Query(value = "SELECT * FROM task order by due_date desc",  nativeQuery = true)
	public List<Task> getAllTask();
	
	@Query(value = "SELECT new com.project.dto.CountType(COUNT(*)/(Select COUNT(*) from Task) * 100,type) FROM Task GROUP BY type")
	public List<CountType> getGroupByType();
}

//TRUNCATE((count(*)/(Select count(*) from `task`))*100, 2)