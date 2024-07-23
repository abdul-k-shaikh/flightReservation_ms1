package com.flightreservation.services;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entities.Reservation;

public interface ReservationService {
	 Reservation bookFlight(ReservationRequest request);
}
