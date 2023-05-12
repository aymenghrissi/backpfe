package com.cnstn.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("salle")
public class SalleController {
	@Autowired
	BackServiceSalle backServiceSalle;
	@Autowired
    private HttpServletRequest request;
	@PostMapping
	public Salle AddSalle(@RequestBody Salle salle){
        Salle savedSalle = backServiceSalle.addSalle(salle);
        String ipAddress = request.getHeader("X-Forwarded-For");
	    String hostName = null ;
		String userAgent = null;
		 Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateTime = dateFormat.format(date);
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	         hostName = request.getRemoteHost();
	         userAgent = request.getHeader("User-Agent");
	    }
	    try {
	        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
	        writer.write("ajouter une salle :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
        String ipAddress = request.getHeader("X-Forwarded-For");
	    String hostName = null ;
		String userAgent = null;
		 Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateTime = dateFormat.format(date);
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	         hostName = request.getRemoteHost();
	         userAgent = request.getHeader("User-Agent");   
	    }
	    try {
	        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
	        writer.write("effacer une salle :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return ResponseEntity.noContent().build();
    }
@PutMapping("/salles/{id}")
    
    public ResponseEntity<Salle> updateSalle(@PathVariable("id") Long id,
                                           @RequestBody Salle Updatesalle){
	Salle service=backServiceSalle.LoadSalleById(id); 
	 service.setNom(Updatesalle.getNom());
	 service.setNombre_place(Updatesalle.getNombre_place());
	 Salle directionmaj= backServiceSalle.addSalle(service);
	 String ipAddress = request.getHeader("X-Forwarded-For");
	    String hostName = null ;
		String userAgent = null;
		 Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateTime = dateFormat.format(date);
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	         hostName = request.getRemoteHost();
	         userAgent = request.getHeader("User-Agent");
	        
	    }
	    try {
	        FileWriter writer = new FileWriter("C:\\Users\\zifor\\Desktop\\frontcnstn\\frontendcnstn\\src\\assets\\logs.txt", true); // set the second argument to true to append to the file
	        writer.write("mise a jour une salle :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }

}
