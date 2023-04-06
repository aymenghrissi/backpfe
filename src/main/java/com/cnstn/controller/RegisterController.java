package com.cnstn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnstn.entities.Employee;
import com.cnstn.service.BackServiceEmployee;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("register")
public class RegisterController {
	@Autowired
	BackServiceEmployee backServicEmployee;
	@PostMapping
	public Employee createEmp(@RequestBody Employee employee) {
		Employee savedEmp = backServicEmployee.addEmployee(employee);
		return savedEmp;
	}

}
