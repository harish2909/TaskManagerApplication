package com.jaza.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaza.springboot.rest.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, String>{

	public Label findByName(String name);
	public Label deleteByName(String name);
}
