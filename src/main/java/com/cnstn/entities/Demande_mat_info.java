package com.cnstn.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande_mat_info {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private Date date_demmande;
	private Date materiel_demmander;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
    @JsonBackReference("employee-demande_mat_info")
	private Employee employee;
	
	

}
