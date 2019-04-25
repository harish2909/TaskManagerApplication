package com.jaza.springboot.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component

@Entity
@Table(name= "task")
public class Task {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	 @Column(name = "name")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="label_name", 
					insertable=true, updatable=true, 
					nullable=false)
	public Label label;
	@Temporal(TemporalType.DATE)
	 @Column(name = "startdate")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	 @Column(name = "enddate")
	private Date endDate;
	 @Column(name = "priority")
	private String priority;
	 
	 public Task() {
		 
	 }
	
	public Task(long id, String name,Label label,Date startDate, Date endDate, String priority) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
	}
	
	public Task(String name,Label label, Date startDate, Date endDate, String priority) {
		super();
		this.name = name;
		this.label = label;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priority=" + priority + "]";
	}

}
