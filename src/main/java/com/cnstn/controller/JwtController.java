package com.cnstn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cnstn.entities.jwtRequest;
import com.cnstn.entities.jwtResponse;
import com.cnstn.service.JwtService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600 ,allowedHeaders = "*")
public class JwtController {
	@Autowired
	private JwtService jwtService ;
	@PostMapping({"/authenticate"})
	public jwtResponse createJwtToken(@RequestBody jwtRequest jwtRequest) throws Exception {
		
		return  jwtService.createJwtToken(jwtRequest);	
	}
	
}
