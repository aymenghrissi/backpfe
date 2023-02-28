package com.cnstn.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnstn.dao.DemandeRepository;
import com.cnstn.entities.Demande_mat_info;
import com.cnstn.entities.Direction;
import com.cnstn.service.BackServiceDemande;

@Service
public class BackServiceDemandeImpl implements BackServiceDemande {
	@Autowired
	DemandeRepository demandeRepository ;

	@Override
	public List<Demande_mat_info> AfficherListDemande() {
		List<Demande_mat_info> Listdemande=demandeRepository.findAll();
		return Listdemande;
	}

	@Override
	public Demande_mat_info addDemande(Demande_mat_info demande) {
		
		return demandeRepository.save(demande);
	}

	@Override
	public Demande_mat_info LoadDemandeById(Long id) {
		Optional<Demande_mat_info> optionaldemande = demandeRepository.findById(id);
        return optionaldemande.get();
	}

	@Override
	public void deleteDemande(Long id) {
		 demandeRepository.deleteById(id);
		
	}

	@Override
	public Demande_mat_info updateDemande(Demande_mat_info demande) {
		Demande_mat_info existingDemande = demandeRepository.findById(demande.getId()).get();
        existingDemande.setDate_demmande(demande.getDate_demmande());
        existingDemande.setMateriel_demmander(demande.getMateriel_demmander());
        Demande_mat_info updatedDemande = demandeRepository.save(existingDemande);
        return updatedDemande;
	}

	

}
