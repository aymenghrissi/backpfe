package com.cnstn.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String nom;
	private String description;
	
	
	@OneToMany(mappedBy ="direction", fetch = FetchType.LAZY , cascade  = CascadeType.ALL, orphanRemoval  = true)
	//@JsonIgnoreProperties("direction")
	private List<Service> sevices = new ArrayList<Service>();

	
	
	
	
	

}
