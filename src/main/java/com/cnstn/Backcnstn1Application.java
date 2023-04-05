package com.cnstn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class Backcnstn1Application {
	@Bean	
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	public static void main(String[] args) {
		SpringApplication.run(Backcnstn1Application.class, args);
	}

}
