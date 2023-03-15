package com.cnstn.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.cnstn.service.JwtService;

public class jwtResponse {
	private Employee employee ;
	private String jwtToken ;
	public jwtResponse(Employee employee, String jwtToken) {
		this.employee = employee ;
		this.jwtToken = jwtToken ;

	}
	public Employee getUser() {
		return employee;
	}
	public void setUser(Employee user) {
		this.employee = user;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	

}
