package com.cnstn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnstn.entities.Reservation;
import com.cnstn.entities.Salle;
import com.cnstn.entities.Service;
import com.cnstn.service.BackServiceSalle;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("salle")
public class SalleController {
	@Autowired
	BackServiceSalle backServiceSalle;
	@PostMapping
	public Salle AddSalle(@RequestBody Salle salle){
        Salle savedSalle = backServiceSalle.addSalle(salle);
        return savedSalle;
    }
	
	@GetMapping("list")
	 public List<Salle> getAllSalle(){
	        List<Salle> salle = backServiceSalle.AfficherListSalle();
	        return salle;
	    }
	@GetMapping("{id}")
    public Salle LoadSalleById(@PathVariable("id") Long id){
        Salle salle = backServiceSalle.LoadSalleById(id);
        return salle;
    }
	@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteSalle(@PathVariable("id") Long id){
        backServiceSalle.deleteSalle(id);
        return ResponseEntity.noContent().build();
    }
@PutMapping("/salles/{id}")
    
    public ResponseEntity<Salle> updateSalle(@PathVariable("id") Long id,
                                           @RequestBody Salle Updatesalle){
	Salle service=backServiceSalle.LoadSalleById(id); 
	 service.setNom(Updatesalle.getNom());
	 service.setNombre_place(Updatesalle.getNombre_place());
	 Salle directionmaj= backServiceSalle.addSalle(service);
	 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }


}
