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

import com.cnstn.entities.Direction;
import com.cnstn.entities.Service;
import com.cnstn.service.BackServiceDirection;
import com.cnstn.service.BackServiceService;
import com.cnstn.servicesImpl.BackServiceServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("service")
public class ServiceController {
	@Autowired
	BackServiceService  backServiceService;
	@Autowired
    private HttpServletRequest request;
	@PostMapping
	public Service AddService(@RequestBody Service service){
        Service savedService = backServiceService.addService(service);
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
	        writer.write("ajouter un service :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
	        writer.write("effacer une service :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return ResponseEntity.noContent().build();
    }
@PutMapping("/services/{id}")
    public ResponseEntity<Service> updateService(@PathVariable("id") Long id,
                                           @RequestBody Service Updateservice){
	Service service=backServiceService.LoadServiceById(id); 
	 service.setNom(Updateservice.getNom());
	 service.setDescription(Updateservice.getDescription());
	 Service directionmaj= backServiceService.addService(service);
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
	        writer.write("mise a jour d'un service :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }

}
