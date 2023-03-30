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

import com.cnstn.entities.Direction;
import com.cnstn.entities.Service;
import com.cnstn.service.BackServiceDirection;
import com.cnstn.service.BackServiceService;
import com.cnstn.servicesImpl.BackServiceServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("service")
public class ServiceController {
	@Autowired
	BackServiceService  backServiceService;
	
	@PostMapping
	public Service AddService(@RequestBody Service service){
        Service savedService = backServiceService.addService(service);
        return savedService;
    }
	
	@GetMapping("list")
	 public List<Service> getAllService(){
	        List<Service> services = backServiceService.AfficherListService();
	        return services;
	    }
	@GetMapping("{id}")
    public Service LoadServiceById(@PathVariable("id") Long id){
        Service service = backServiceService.LoadServiceById(id);
        return service;
    }
	@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteService(@PathVariable("id") Long id){
        backServiceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
@PutMapping("/services/{id}")
    
    public ResponseEntity<Service> updateService(@PathVariable("id") Long id,
                                           @RequestBody Service Updateservice){
	Service service=backServiceService.LoadServiceById(id); 
	 service.setNom(Updateservice.getNom());
	 service.setDescription(Updateservice.getDescription());
	 Service directionmaj= backServiceService.addService(service);
	 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }

}
