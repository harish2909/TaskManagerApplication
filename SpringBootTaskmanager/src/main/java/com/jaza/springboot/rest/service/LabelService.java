package com.jaza.springboot.rest.service;

import java.util.List;

import com.jaza.springboot.rest.model.Label;

public interface LabelService {
	public List<Label> getLabels();

	public Label getLabelByName(String name);

	public Label addNewLabel(Label newLabel);

	public Label updateLabel(Label updlabel);

	public void deleteLabelByName(String name);

	public void deleteAllLabels();

}
