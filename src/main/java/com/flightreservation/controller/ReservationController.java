package com.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entities.Flight;
import com.flightreservation.entities.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class); 


	@GetMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		LOGGER.info("showCompleteReservation() invoked with the flight id: "+flightId);
		Flight flight = flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";

	}

	@PostMapping("/completeReservation")
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		LOGGER.info("completeReservation() "+ request);
		
		Reservation reservation = reservationService.bookFlight(request);

		modelMap.addAttribute("msg",
				"Reservation created successfully and the reservation id is " + reservation.getId());

		return "reservationConfirmation";

	}

}
