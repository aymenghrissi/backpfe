package com.cnstn.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cnstn.dao.EmployeeRepository;
import com.cnstn.entities.Employee;
import com.cnstn.entities.jwtRequest;
import com.cnstn.entities.jwtResponse;
import com.cnstn.util.JwtUtil;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
@Component
public class JwtService implements UserDetailsService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 Employee employee =employeeRepository.findByEmail(username).get();
	 if(employee != null  ) {
		 return new User(employee.getEmail() ,employee.getPassword(),getAuthorities(employee));

	 }else {
		 throw new UsernameNotFoundException("username is not valid ");
	 }
	}
	private Set getAuthorities(Employee employee ){
		Set authorities = new HashSet<>();
		employee.getApproles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		return authorities ;
	}
	public jwtResponse createJwtToken(jwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getPassword();
		authenticate(userName,userPassword);
		final UserDetails userDetails = loadUserByUsername(userName);
		String newgeneratedToken = jwtUtil.generateToken(userDetails);
		Employee employee = employeeRepository.findByEmail(userName).get();
		return new jwtResponse(employee,newgeneratedToken);
		
	}
	private void authenticate(String userName, String userPassword) throws Exception {
	    try {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
	    } catch (DisabledException e) {
	        throw new Exception("User is disabled");
	    } catch (BadCredentialsException e) {
	        throw new Exception("Invalid username or password");
	    }
	}
}
