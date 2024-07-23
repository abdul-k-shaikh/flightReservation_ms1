package com.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightreservation.dto.ReservationUpdateRequest;
import com.flightreservation.entities.Reservation;
import com.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	ReservationRepository reservationRepository;
	
//	@PathVariable. This allows you to access dynamic values from the 
//	URL and use them in your code. 
//	 @RequestParam annotation enables you to extract data from the query 
//	 parameters in the request URL. Query parameters are key-value pairs appended to the URL after 
//	a question mark (?)
//	@GetMapping("/reservations/{id}")
//	public Optional<Reservation> findReservation(@PathVariable("id") Long id) {
//		 Optional<Reservation> reservation =
//		LOGGER.info("Inside findReservation() for id: " + id);
//		return  reservationRepository.findById(id).get();
		
//	}
	@GetMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id: " + id);
		return reservationRepository.findById(id).get();

	}
	
//	@RequestMapping(value= "/reservations",method = RequestMethod.PUT)
    @PutMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() for " + request);
		System.out.println("Inside updateReservation() for " + request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation");
		return reservationRepository.save(reservation);
		
	}
}
