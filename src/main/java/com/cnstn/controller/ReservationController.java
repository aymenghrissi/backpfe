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
import com.cnstn.entities.Reservation;
import com.cnstn.service.BackServiceReservation;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("reservation")
public class ReservationController {
	@Autowired
	BackServiceReservation backServiceReservation ;
	@Autowired
    private HttpServletRequest request;
	@PostMapping
	public Reservation AddReservation(@RequestBody Reservation reservation){
        Reservation savedReservation = backServiceReservation.addReservation(reservation);
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
	        writer.write("ajouter une reservation :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return savedReservation;
    }
	
	@GetMapping("list")
	 public List<Reservation> getAllReservations(){
	        List<Reservation> reservation = backServiceReservation.AfficherListRes();
	        return reservation;
	    }
	@GetMapping("{id}")
    public Reservation LoadReservationById(@PathVariable("id") Long id){
        Reservation reservation = backServiceReservation.LoadReservationById(id);
        return reservation;
    }
	@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable("id") Long id){
        backServiceReservation.deleteReservation(id);
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
	        writer.write("effacer une reservation :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        return ResponseEntity.noContent().build();
    }
@PutMapping("/reservations/{id}")
    
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id,
                                           @RequestBody Reservation updatereservation){
	Reservation res=backServiceReservation.LoadReservationById(id); 
	 res.setDate_d(updatereservation.getDate_d());
	 res.setDate_f(updatereservation.getDate_f());
	 Reservation directionmaj= backServiceReservation.addReservation(res);
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
	        writer.write("mise a jour d'une reservation :"+ipAddress + ", " + hostName  + ", " + userAgent + ", " + dateTime +  "\n");
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }

}
