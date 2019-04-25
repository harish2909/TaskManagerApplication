package com.jaza.springboot.rest.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaza.springboot.rest.model.Task;
import com.jaza.springboot.rest.repository.TaskRepository;
import com.jaza.springboot.rest.repository.TaskRepositoryCustom;
import com.jaza.springboot.rest.vo.LabelVo;
import com.jaza.springboot.rest.vo.PriorityVo;
import com.jaza.springboot.rest.vo.SearchRequestVo;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	TaskRepositoryCustom searchTaskRepository;

	@Override
	public List<Task> getTasks() {
		return (List<Task>) taskRepo.findAll();
	}

	@Override
	public Optional<Task> getTaskById(long id) {
		return taskRepo.findById(id);
	}

	@Override
	public Task addNewTask(Task newtask){
		return taskRepo.save(newtask);
	}

	@Override
	public Task updateTask(Task updtask) {
		return taskRepo.save(updtask);
	}

	@Override
	public void deleteTaskById(long id) {
		taskRepo.deleteById(id);
	}

	@Override
	public void deleteAllTasks() {
		taskRepo.deleteAll();
	}

	@Override
	public List<Task> searchTask(SearchRequestVo searchRequest) {
		List<Task> taskList = null;
		Date startDateCondition = null;
		Date endDateCondition = null;
		startDateCondition = dateFilterCheckWithStartDate(searchRequest);
		endDateCondition = dateFilterCheckWithEndDate(searchRequest);
		if (isDateCheckNoOfDays(startDateCondition, endDateCondition, searchRequest)) {
			Calendar cal = Calendar.getInstance();
			if (searchRequest.getNoOfdays() != 0 && searchRequest.isPrevious()) {
				endDateCondition = cal.getTime();
				cal.add(Calendar.DAY_OF_MONTH, -(searchRequest.getNoOfdays()));
				startDateCondition = cal.getTime();
			} else if (searchRequest.getNoOfdays() != 0 && searchRequest.isNext()) {
				startDateCondition = cal.getTime();
				cal.add(Calendar.DAY_OF_MONTH, searchRequest.getNoOfdays());
				endDateCondition = cal.getTime();
			}
		}
		List<String> priorities = getPrioritiesList(searchRequest.getPriority());
		List<String> labels = getLabelsList(searchRequest.getLabel());
		taskList = checkWhetherAllValuesEmpty(startDateCondition, endDateCondition, priorities, labels);
		if (taskList == null) {
			StringBuilder filter = new StringBuilder("select t from Task t inner join t.label l  where ");
			getDynamicWhereClause(startDateCondition, endDateCondition, priorities, labels, filter);
			return searchTaskRepository.searchTasks(startDateCondition, endDateCondition, priorities, labels, filter);
		}
		return taskList;
	}

	private List<Task> checkWhetherAllValuesEmpty(Date startDateCondition, Date endDateCondition,
			List<String> priorities, List<String> labels) {
		if (startDateCondition == null && endDateCondition == null && priorities.isEmpty() && labels.isEmpty()) {
			return taskRepo.findAll();
		}
		return null;
	}

	private boolean isDateCheckNoOfDays(Date startDateCondition, Date endDateCondition, SearchRequestVo searchRequest) {
		if (startDateCondition == null && endDateCondition == null && searchRequest.getNoOfdays() != 0
				&& (searchRequest.isNext() || searchRequest.isPrevious())) {
			return true;
		}
		return false;
	}

	private List<Task> getDynamicWhereClause(Date startDateCondition, Date endDateCondition, List<String> priorities,
			List<String> labels, StringBuilder filter) {
		if (startDateCondition == null && endDateCondition == null && priorities.isEmpty() && labels.isEmpty()) {
			return taskRepo.findAll();
		} else if (startDateCondition != null && endDateCondition != null) {
			filter.append("t.startDate >= :startDateCondition and t.endDate <= :endDateCondition ");
			if (!priorities.isEmpty() || !labels.isEmpty()) {
				filter.append("and ");
			}
		}
		addPriorityCondition(priorities, labels, filter);
		addLabelsCondition(labels, filter);
		return null;
	}

	private void addLabelsCondition(List<String> labels, StringBuilder filter) {
		if (!labels.isEmpty()) {
			filter.append("l.label in (:labels)");
		}
	}

	private void addPriorityCondition(List<String> priorities, List<String> labels, StringBuilder filter) {
		if (!priorities.isEmpty()) {
			filter.append("t.priority in (:priorities) ");
			if (!labels.isEmpty()) {
				filter.append("and ");
			}
		}
	}

	private List<String> getLabelsList(List<LabelVo> label) {
		List<String> labelList = new ArrayList<>();
		Iterator<LabelVo> iterator = label.iterator();
		while (iterator.hasNext()) {
			LabelVo labelVo = iterator.next();
			if (labelVo.getName() != null && !labelVo.getName().isEmpty()) {
				labelList.add(labelVo.getName());
			}
		}
		return labelList;
	}

	private List<String> getPrioritiesList(List<PriorityVo> priority) {
		List<String> priorityList = new ArrayList<>();
		Iterator<PriorityVo> iterator = priority.iterator();
		while (iterator.hasNext()) {
			PriorityVo priorityVo = iterator.next();
			if (priorityVo.getName() != null && !priorityVo.getName().isEmpty()) {
				priorityList.add(priorityVo.getName());
			}
		}
		return priorityList;
	}

	private Date dateFilterCheckWithStartDate(SearchRequestVo searchRequest) {
		Calendar cal = Calendar.getInstance();
		Date startDate = null;
		if (searchRequest.getStartDate() != null && searchRequest.getEndDate() == null
				&& searchRequest.getNoOfdays() == 0 && searchRequest.isNext() == false
				&& searchRequest.isPrevious() == false) {
			cal.setTime(searchRequest.getStartDate());
			startDate = cal.getTime();
		} else if (searchRequest.getStartDate() != null && searchRequest.getEndDate() != null
				&& searchRequest.getNoOfdays() == 0 && searchRequest.isNext() == false
				&& searchRequest.isPrevious() == false) {
			cal.setTime(searchRequest.getStartDate());
			startDate = cal.getTime();
		} else if (searchRequest.getStartDate() == null && searchRequest.getEndDate() != null
				&& searchRequest.getNoOfdays() == 0 && searchRequest.isNext() == false
				&& searchRequest.isPrevious() == false) {
			startDate = cal.getTime();
		}
		return startDate;
	}

	private Date dateFilterCheckWithEndDate(SearchRequestVo searchRequest) {
		Calendar cal = Calendar.getInstance();
		Date endDate = null;
		if (searchRequest.getStartDate() == null && searchRequest.getEndDate() != null
				&& searchRequest.getNoOfdays() == 0 && searchRequest.isNext() == false
				&& searchRequest.isPrevious() == false) {
			cal.setTime(searchRequest.getEndDate());
			endDate = cal.getTime();
		} else if (searchRequest.getStartDate() != null && searchRequest.getEndDate() != null
				&& searchRequest.getNoOfdays() == 0 && searchRequest.isNext() == false
				&& searchRequest.isPrevious() == false) {
			cal.setTime(searchRequest.getEndDate());
			endDate = cal.getTime();
		} else if (searchRequest.getStartDate() != null && searchRequest.getEndDate() == null
				&& searchRequest.getNoOfdays() == 0 && searchRequest.isNext() == false
				&& searchRequest.isPrevious() == false) {
			cal.setTime(searchRequest.getStartDate());
			endDate = cal.getTime();
		}
		return endDate;
	}

}
