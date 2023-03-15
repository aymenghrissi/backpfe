package com.cnstn.service;

import java.util.List;

import com.cnstn.entities.Employee;
import com.cnstn.entities.approle;

public interface BackServiceEmployee {
	List<Employee> AfficherListEmp();
	Employee addEmployee(Employee employee);
	Employee LoadEmployeeById(Long id);
	void deleteEmployee(Long id);
	Employee updateEmployee(Employee employee);
	approle addrole(approle role);
	void addRoleToUser(Long id , String RoleName);
	

}
