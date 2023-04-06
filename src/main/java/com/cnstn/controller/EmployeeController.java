package com.cnstn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnstn.entities.Direction;
import com.cnstn.entities.Employee;
import com.cnstn.service.BackServiceEmployee;
import com.cnstn.servicesImpl.BackServiceEmployeeImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	BackServiceEmployee backServicEmployee;

	@PostMapping
	public Employee createEmp(@RequestBody Employee employee) {
		Employee savedEmp = backServicEmployee.addEmployee(employee);
		return savedEmp;
	}

	@GetMapping("list")
	public List<Employee> getAllEmp() {
		List<Employee> employees = backServicEmployee.AfficherListEmp();
		return employees;
	}

	@GetMapping("{id}")
	public Employee LoadEmployeeById(@PathVariable("id") Long id) {
		Employee employee = backServicEmployee.LoadEmployeeById(id);
		return employee;
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id) {
		backServicEmployee.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/employees/{id}")

	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
		Employee direction=backServicEmployee.LoadEmployeeById(id);
		 direction.setNom(employee.getNom());
		 direction.setPrenom(employee.getPrenom());
		 direction.setCin(employee.getCin());
		 direction.setTel(employee.getTel());
		 direction.setAdresse(employee.getAdresse());
		 direction.setPoste(employee.getPoste());
		 direction.setMatricule(employee.getMatricule());
		 direction.setEmail(employee.getEmail());
		 direction.setTel_interne(employee.getTel_interne());
		 direction.setRole(employee.getRole());
		 Employee directionmaj= backServicEmployee.addEmployee(employee);
		 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
	}
}
