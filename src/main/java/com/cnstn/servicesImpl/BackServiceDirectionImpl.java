package com.cnstn.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnstn.dao.DirectionRepository;

import com.cnstn.entities.Direction;

import com.cnstn.service.BackServiceDirection;

@Service
public class BackServiceDirectionImpl implements BackServiceDirection{
	

	
	@Autowired
	DirectionRepository directionRepository;





	@Override
	public List<Direction> AfficherListDir() {
		List<Direction> Listdir=directionRepository.findAll();
		return Listdir;
	}

	@Override
	public Direction addDirections(Direction direction) {
		
		return directionRepository.save(direction);
	}

	@Override
	public Direction LoadDirectionById( Long id) {
		Optional<Direction> optionaldirection = directionRepository.findById(id);
        return optionaldirection.get();
	}

	@Override
	public void deleteDirection(Long id) {
		 directionRepository.deleteById(id);
		
	}

	@Override
	public Direction updateDirection(Direction direction) {
		Direction existingDirection = directionRepository.findById(direction.getId()).get();
        existingDirection.setDescription(direction.getDescription());
        existingDirection.setNom(direction.getNom());
        Direction updatedDirection = directionRepository.save(existingDirection);
        return updatedDirection;
	}

	

	
	

	
}


