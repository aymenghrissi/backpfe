package com.cnstn.service;

import java.util.List;

import com.cnstn.entities.Salle;


public interface BackServiceSalle {
	
	List<Salle> AfficherListSalle();
	Salle addSalle(Salle salle );
	Salle LoadSalleById(Long id);
	void deleteSalle(Long id);
	Salle updateSalle(Salle salle);

}
