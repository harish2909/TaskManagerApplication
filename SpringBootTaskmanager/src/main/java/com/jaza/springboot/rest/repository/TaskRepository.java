package com.jaza.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaza.springboot.rest.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
