package com.jaza.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaza.springboot.rest.model.Task;
import com.jaza.springboot.rest.service.TaskService;
import com.jaza.springboot.rest.vo.SearchRequestVo;

@RestController
public class Taskcontroller {

	@Autowired
	TaskService service;

	@RequestMapping(value= "/tasks/all", method= RequestMethod.GET)
	public List<Task> getTasks() {
		return service.getTasks();
	}

	@RequestMapping(value= "/tasks/{id}", method= RequestMethod.GET)
	public Task getTaskById(@PathVariable long id) throws Exception {
		Optional<Task> task =  service.getTaskById(id);
		if(!task.isPresent())
			throw new Exception("Could not find task with id- " + id);
		return task.get();
	}

	@RequestMapping(value= "/tasks/add", method= RequestMethod.POST)
	public Task createTask(@RequestBody Task newtask) {
		return service.addNewTask(newtask);
	}

	@RequestMapping(value= "/tasks/update/{id}", method= RequestMethod.PUT)
	public Task updateTask(@RequestBody Task updtask, @PathVariable long id) throws Exception {
		Optional<Task> task =  service.getTaskById(id);
		if (!task.isPresent())
			throw new Exception("Could not find task with id- " + id);
		if(updtask.getName() == null || updtask.getName().isEmpty())
			updtask.setName(task.get().getName());
		if(updtask.getStartDate() == null)
			updtask.setStartDate(task.get().getStartDate());
		if(updtask.getEndDate() == null)
			updtask.setEndDate(task.get().getEndDate());
		if(updtask.getPriority()== null || updtask.getPriority().isEmpty())
			updtask.setPriority(task.get().getPriority());
		if(updtask.label.getName()==null || updtask.label.getName().isEmpty())
			updtask.label.setName(task.get().label.getName());
		if(updtask.label.getLabel()==null || updtask.label.getLabel().isEmpty())
			updtask.label.setLabel(task.get().label.getLabel());

		// Required for the "where" clause in the sql query template.
		updtask.setId(id);
		return service.updateTask(updtask);
	}

	@RequestMapping(value= "/tasks/delete/{id}", method= RequestMethod.DELETE)
	public void deleteTaskById(@PathVariable long id) throws Exception {
		Optional<Task> task =  service.getTaskById(id);
		if(!task.isPresent())
			throw new Exception("Could not find task with id- " + id);
		service.deleteTaskById(id);
	}

	@RequestMapping(value= "/tasks/deleteall", method= RequestMethod.DELETE)
	public void deleteAll() {
		service.deleteAllTasks();
	}
	
	@RequestMapping(value= "/tasks/search", method= RequestMethod.POST)
	public List<Task> searchTask(@RequestBody SearchRequestVo searchRequest) {
		return service.searchTask(searchRequest);
	}
}
