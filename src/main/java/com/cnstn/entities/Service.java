package com.cnstn.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String description;
	
	@ManyToOne
    @JoinColumn(name = "direction_id")
    @JsonBackReference("service-direction")
	Direction direction;
	
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
	//@JsonIgnoreProperties("service")
	private List<Employee> employees= new ArrayList<Employee>();

	

	
	
}

