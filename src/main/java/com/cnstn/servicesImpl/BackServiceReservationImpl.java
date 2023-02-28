package com.cnstn.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnstn.dao.ReservationRepository;
import com.cnstn.entities.Reservation;

import com.cnstn.service.BackServiceReservation;
@Service
public class BackServiceReservationImpl implements BackServiceReservation {
	@Autowired
	ReservationRepository reservationRepository ;

	@Override
	public List<Reservation> AfficherListRes() {
		 List<Reservation> ListRes=reservationRepository.findAll() ;
		return ListRes;
	}

	@Override
	public Reservation addReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation LoadReservationById(Long id) {
		Optional<Reservation> optionalreservation = reservationRepository.findById(id);
        return optionalreservation.get();
	}

	@Override
	public void deleteReservation(Long id) {
		reservationRepository.deleteById(id);
		
	}

	@Override
	public Reservation updateReservation(Reservation reservation) {
		Reservation existingReservation = reservationRepository.findById(reservation.getId()).get();
        existingReservation.setDate_d(reservation.getDate_d());
        existingReservation.setDate_f(reservation.getDate_f());
        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return updatedReservation;
	}

}
