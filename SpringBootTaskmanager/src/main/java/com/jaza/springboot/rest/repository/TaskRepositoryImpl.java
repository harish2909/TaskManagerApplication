package com.jaza.springboot.rest.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jaza.springboot.rest.model.Task;

@Repository
@Transactional("transactionManager")
public class TaskRepositoryImpl implements TaskRepositoryCustom{
	
	@PersistenceContext(name = "entityManagerFactory", unitName = "Persistence")
    private EntityManager entityManager;
	
	@Override
	public List<Task> searchTasks(Date startDateCondition, Date endDateCondition, List<String> priorities,
			List<String> labels, StringBuilder filter) {
		List<Task> task = new ArrayList<>();
		try {
		Query query = entityManager.createQuery(filter.toString());
		if(startDateCondition!=null) {
			query.setParameter("startDateCondition", startDateCondition);}
		if(startDateCondition!=null) {
        query.setParameter("endDateCondition", endDateCondition);}
		if(!priorities.isEmpty()) {
        query.setParameter("priorities", priorities);}
		if(!labels.isEmpty()) {
        query.setParameter("labels", labels);}
       task = (List<Task>) query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
        return task;
	}
	
	

}
