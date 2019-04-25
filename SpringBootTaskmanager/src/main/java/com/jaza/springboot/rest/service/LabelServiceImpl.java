package com.jaza.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaza.springboot.rest.model.Label;
import com.jaza.springboot.rest.repository.LabelRepository;

@Service
public class LabelServiceImpl implements LabelService{

	@Autowired
	LabelRepository labelRepo;
	
	@Override
	public List<Label> getLabels() {
		return (List<Label>) labelRepo.findAll();
	}

	@Override
	public Label getLabelByName(String name) {
		return labelRepo.findByName(name);
	}

	@Override
	public Label addNewLabel(Label newLabel) {
		return labelRepo.save(newLabel);
	}

	@Override
	public Label updateLabel(Label updlabel) {
		return labelRepo.save(updlabel);
	}

	@Override
	public void deleteLabelByName(String name) {
		labelRepo.deleteByName(name);
	}

	@Override
	public void deleteAllLabels() {
		labelRepo.deleteAll();
	}

}
