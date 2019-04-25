package com.jaza.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaza.springboot.rest.model.Label;
import com.jaza.springboot.rest.service.LabelService;

@RestController
public class LabelController {
	

		@Autowired
		LabelService service;

		@RequestMapping(value= "/labels/all", method= RequestMethod.GET)
		public List<Label> getLabels() {
			return service.getLabels();
		}

		@RequestMapping(value= "/labels/{name}", method= RequestMethod.GET)
		public Label getLabelByName(@PathVariable String name) throws Exception {
			Label label =  service.getLabelByName(name);
			return label;
		}

		@RequestMapping(value= "/labels/add", method= RequestMethod.POST)
		public Label createLabel(@RequestBody Label newLabel) {
			return service.addNewLabel(newLabel);
		}

		@RequestMapping(value= "/labels/update/{name}", method= RequestMethod.PUT)
		public Label updateLabel(@RequestBody Label updlabel, @PathVariable String name) throws Exception {
			Label label =  service.getLabelByName(name);
			if(label.getLabel()==null) {
				throw new Exception("Could not find label with name- " + name);
			}
			if(updlabel.getName() == null || updlabel.getName().isEmpty())
				updlabel.setName(label.getName());
			if(updlabel.getLabel() == null || updlabel.getLabel().isEmpty())
				updlabel.setLabel(label.getLabel());
			updlabel.setName(name);
			return service.updateLabel(updlabel);
		}

		@RequestMapping(value= "/labels/delete/{name}", method= RequestMethod.DELETE)
		public void deleteLabelByName(@PathVariable String name) throws Exception {
			Label label =  service.getLabelByName(name);
			if(label.getLabel()==null) {
				throw new Exception("Could not find label with name- " + name);
			}
			service.deleteLabelByName(name);
		}

		@RequestMapping(value= "/labels/deleteall", method= RequestMethod.DELETE)
		public void deleteAll() {
			service.deleteAllLabels();
		}

}
