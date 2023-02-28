package com.cnstn.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnstn.dao.SalleRepository;
import com.cnstn.entities.Reservation;
import com.cnstn.entities.Salle;
import com.cnstn.service.BackServiceSalle;

@Service
public class BackServiceSalleImpl implements BackServiceSalle {

	@Autowired
	SalleRepository salleRepository ;
	@Override
	public List<Salle> AfficherListSalle() {
		List<Salle> ListSalle=salleRepository.findAll() ;
		return ListSalle;
	}

	@Override
	public Salle addSalle(Salle salle) {
		return salleRepository.save(salle);
	}

	@Override
	public Salle LoadSalleById(Long id) {
		Optional<Salle> optionalSalle = salleRepository.findById(id);
        return optionalSalle.get();
	}

	@Override
	public void deleteSalle(Long id) {
		salleRepository.deleteById(id);
		
	}

	@Override
	public Salle updateSalle(Salle salle) {
		Salle existingSalle = salleRepository.findById(salle.getId()).get();
        existingSalle.setNom(salle.getNom());
        existingSalle.setNombre_place(salle.getNombre_place());
        Salle updatedSalle = salleRepository.save(existingSalle);
        return updatedSalle;
	}

}
