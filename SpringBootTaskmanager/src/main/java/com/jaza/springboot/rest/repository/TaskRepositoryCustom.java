package com.jaza.springboot.rest.repository;

import java.util.Date;
import java.util.List;

import com.jaza.springboot.rest.model.Task;

public interface TaskRepositoryCustom {

	public List<Task> searchTasks(Date startDateCondition, Date endDateCondition, List<String> priorities,
			List<String> labels, StringBuilder filter);

}
