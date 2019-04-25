package com.jaza.springboot.rest.vo;

import java.util.Date;
import java.util.List;

public class SearchRequestVo {
	
	private int noOfdays;
	private boolean previous;
	private boolean next;
	private Date startDate;
	private Date endDate;
	private List<PriorityVo> priority;
	private List<LabelVo> label;
	public int getNoOfdays() {
		return noOfdays;
	}
	public void setNoOfdays(int noOfdays) {
		this.noOfdays = noOfdays;
	}
	public boolean isPrevious() {
		return previous;
	}
	public void setPrevious(boolean previous) {
		this.previous = previous;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
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
	public List<PriorityVo> getPriority() {
		return priority;
	}
	public void setPriority(List<PriorityVo> priority) {
		this.priority = priority;
	}
	public List<LabelVo> getLabel() {
		return label;
	}
	public void setLabel(List<LabelVo> label) {
		this.label = label;
	}
	
	

}
