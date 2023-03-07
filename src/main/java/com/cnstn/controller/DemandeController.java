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

import com.cnstn.entities.Demande_mat_info;
import com.cnstn.entities.Direction;
import com.cnstn.service.BackServiceDemande;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("demande")
public class DemandeController {
	@Autowired
	BackServiceDemande backServiceDemande;
	@PostMapping
	public Demande_mat_info createDemande(@RequestBody Demande_mat_info demande){
        Demande_mat_info savedDemande = backServiceDemande.addDemande(demande);
        return savedDemande;
    }
	
	@GetMapping("demandes")
	 public List<Demande_mat_info> getAllDemande(){
	        List<Demande_mat_info> demande = backServiceDemande.AfficherListDemande();
	        return demande;
	    }
	@GetMapping("{id}")
	    public Demande_mat_info LoadDemandeById(@PathVariable("id") Long id){
	        Demande_mat_info demande = backServiceDemande.LoadDemandeById(id);
	        return demande;
	    }
	@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteDirection(@PathVariable("id") Long id){
        backServiceDemande.deleteDemande(id);
        return ResponseEntity.noContent().build();
    }
	@PutMapping("{id}")
    
    public ResponseEntity<Demande_mat_info> updateDemande(@PathVariable("id") Long id,
                                           @RequestBody Demande_mat_info demande){
        demande.setId(id);
        Demande_mat_info updatedDemande = backServiceDemande.updateDemande(demande);
        return new ResponseEntity<>(updatedDemande, HttpStatus.OK);
    }

}
