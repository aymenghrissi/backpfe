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

import com.cnstn.entities.Demande_mat_info;
import com.cnstn.entities.Direction;
import com.cnstn.service.BackServiceDemande;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("demande")
public class DemandeController {
	@Autowired
	BackServiceDemande backServiceDemande;
	@Autowired
    private HttpServletRequest request;
	@PostMapping
	public Demande_mat_info createDemande(@RequestBody Demande_mat_info demande){
        Demande_mat_info savedDemande = backServiceDemande.addDemande(demande);
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
	        writer.write("fait une demande :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
	        writer.write("effacer une demande :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return ResponseEntity.noContent().build();
    }
	@PutMapping("/demandes/{id}")
    public ResponseEntity<Demande_mat_info> updateDemande(@PathVariable("id") Long id,
                                           @RequestBody Demande_mat_info Updatedemande){
    	Demande_mat_info demande=backServiceDemande.LoadDemandeById(id); 
		 demande.setDate_demmande(Updatedemande.getDate_demmande());
		 demande.setMateriel_demmander(Updatedemande.getMateriel_demmander());
		 Demande_mat_info directionmaj= backServiceDemande.addDemande(demande);
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
		        writer.write("mise a jour une demande :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }

}
