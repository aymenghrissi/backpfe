package com.cnstn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Backcnstn1Application {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET","PUT","POST","DELETE","OPTIONS")
				.allowedHeaders("*")
				.allowedOriginPatterns("*")
				.allowCredentials(true).maxAge(3600);
				
			}
		};
	}

	@Bean	
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	public static void main(String[] args) {
		SpringApplication.run(Backcnstn1Application.class, args);
	}

}
