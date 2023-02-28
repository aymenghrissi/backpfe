package com.cnstn.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
public class Salle {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String nom;
	private int nombre_place;
	
	
	
	@OneToMany(mappedBy = "salle", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("salle")
	private List<Reservation> reservation= new ArrayList<Reservation>();

}
