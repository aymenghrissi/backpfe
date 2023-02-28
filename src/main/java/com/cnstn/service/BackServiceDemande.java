package com.cnstn.service;

import java.util.List;

import com.cnstn.entities.Demande_mat_info;



public interface BackServiceDemande {
	List<Demande_mat_info> AfficherListDemande();
	Demande_mat_info addDemande(Demande_mat_info demande);
	Demande_mat_info LoadDemandeById(Long id);
	void deleteDemande(Long id);
	Demande_mat_info updateDemande(Demande_mat_info demande);
}
