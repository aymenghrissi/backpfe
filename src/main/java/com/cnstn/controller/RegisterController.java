package com.cnstn.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnstn.entities.Employee;
import com.cnstn.service.BackServiceEmployee;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("register")
public class RegisterController {
	@Autowired
	BackServiceEmployee backServicEmployee;
	@Autowired
    private HttpServletRequest request;
	@PostMapping
	public Employee createEmp(@RequestBody Employee employee) {
		Employee savedEmp = backServicEmployee.addEmployee(employee);
		   String ipAddress = request.getHeader("X-Forwarded-For");
		    String hostName = null ;
			String userAgent = null;
			 Date date = new Date();
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String dateTime = dateFormat.format(date);
			if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
		        ipAddress = request.getRemoteAddr();
		         hostName = request.getRemoteHost();
		         userAgent = request.getHeader("User-Agent");		        
		    }
		    try {
		        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
		        writer.write("registred :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		return savedEmp;
	}
	
}
