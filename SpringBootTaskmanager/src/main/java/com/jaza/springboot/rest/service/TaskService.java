package com.jaza.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import com.jaza.springboot.rest.model.Task;
import com.jaza.springboot.rest.vo.SearchRequestVo;

public interface TaskService {

	public List<Task> getTasks();

	public Optional<Task> getTaskById(long id);

	public Task addNewTask(Task newtask);

	public Task updateTask(Task updtask);

	public void deleteTaskById(long id);

	public void deleteAllTasks();

	public List<Task> searchTask(SearchRequestVo searchRequest);


}
