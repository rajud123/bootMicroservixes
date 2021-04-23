package com.raju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raju.entity.Education;
import com.raju.entity.Employee;
import com.raju.entity.PersonalInfo;
import com.raju.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping(value="/saveEmp",consumes = "application/json")
	public ResponseEntity<String> saveEmp(@RequestBody Employee emp) {
		Employee saveEmp = service.saveEmp(emp);
		if (saveEmp != null)
			return new ResponseEntity<>("employee saved with id::" + saveEmp.getEid(), HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Error creating employee::", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteEmp/{id}")
	public String deleteEmp(Integer id) {
		return service.deleteEmp(id);
		}
	
	@PutMapping(value="/updateEmp",consumes = "application/json")
	public String updateEmp() {
		Employee employee = service.updateEmp();
		return "employee updated";
	}
}
