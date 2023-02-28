package com.cnstn.service;

import java.util.List;

import com.cnstn.entities.Reservation;

public interface BackServiceReservation {
	
	List<Reservation> AfficherListRes();
	Reservation addReservation(Reservation reservation);
	Reservation LoadReservationById(Long id);
	void deleteReservation(Long id);
	Reservation updateReservation(Reservation reservation);
}
