package com.example.demo.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Target;
import com.example.demo.service.TargetService;

@RestController
public class TargetController {
	
	@Autowired
	TargetService targetService;
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) {
		Target targetData = targetService.getProduct(id);
		if(targetData!=null) {
			return new ResponseEntity<>(targetData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getExternalProduct")
	public ResponseEntity<?> getExternalProduct() {
		String targetExternalData = targetService.getExternalProduct();
		if(!StringUtils.isBlank(targetExternalData)) {
			return new ResponseEntity<>(targetExternalData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Target target) {
		Target oldData = targetService.getProduct(id);
		if(oldData!=null) {
			targetService.updateProduct(oldData, target);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
