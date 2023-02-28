package com.cnstn.entities;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type" ,length=4)
public abstract class Materiels {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private long serialnumber;
	private Date dateachat; 
	private String nom;
	private long codeinv;
	 
	@ManyToOne
	@JoinColumn(name = "employee_id")
    @JsonBackReference("employee-matriels")
	Employee employee;
	

}
