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
import com.cnstn.entities.Reservation;
import com.cnstn.service.BackServiceReservation;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("reservation")
public class ReservationController {
	@Autowired
	BackServiceReservation backServiceReservation ;
	@PostMapping
	public Reservation AddReservation(@RequestBody Reservation reservation){
        Reservation savedReservation = backServiceReservation.addReservation(reservation);
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
        return ResponseEntity.noContent().build();
    }
@PutMapping("/reservations/{id}")
    
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id,
                                           @RequestBody Reservation updatereservation){
	Reservation res=backServiceReservation.LoadReservationById(id); 
	 res.setDate_d(updatereservation.getDate_d());
	 res.setDate_f(updatereservation.getDate_f());
	 Reservation directionmaj= backServiceReservation.addReservation(res);
	 return new ResponseEntity<>(directionmaj, HttpStatus.OK);
    }


}
