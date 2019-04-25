package com.jaza.springboot.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.springframework.stereotype.Component;

@Component

@Entity
@Table(name= "label")
public class Label {

	@Id
	@Column(name = "label_name")
	private String name;
	@Column(name = "label")
	private String label;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="label_name")
	private List<Task> tasks;
	
	public Label() {
		
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	
}
