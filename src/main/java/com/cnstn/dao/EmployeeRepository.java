package com.cnstn.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cnstn.entities.Employee;





public interface EmployeeRepository extends JpaRepository <Employee,Long>   {
Optional<Employee> findByEmail(@Param("email") String email);
	
	
	
	
	
}
