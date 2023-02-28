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
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private int cin;
	private int tel;
	private int matricule;
	private String adresse;
	private String poste;

	@ManyToOne
	@JoinColumn(name = "service_id")
    @JsonBackReference("service-employee")
	private Service service;
	
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("employee")
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("employee")
	private List<Materiels> materiels = new ArrayList<Materiels>();

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("employee")
	private List<Demande_mat_info> demandes = new ArrayList<Demande_mat_info>();

}
