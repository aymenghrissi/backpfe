package com.cnstn.service;

import java.util.List;

import com.cnstn.entities.Materiels;


public interface BackServiceMatriel {
	List<Materiels> AfficherListMatriel();
	Materiels addEmployee(Materiels matriel);
	Materiels LoadMatrielById(Long id);
	void deleteMatriel(Long id);
	Materiels updateEmployee(Materiels matriel);

}
