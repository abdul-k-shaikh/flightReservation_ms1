package com.flightreservation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entities.Flight;
import com.flightreservation.entities.Passenger;
import com.flightreservation.entities.Reservation;
import com.flightreservation.repos.FlightRepository;
import com.flightreservation.repos.PassengerRepository;
import com.flightreservation.repos.ReservationRepository;
import com.flightreservation.util.EmailUtil;
import com.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.flightreservation.itinerary.dirpath}")
	private  String ITINERARY_DIR;
//	= "C:/Users/abshaikh/OneDrive - Capgemini/Documents/reservations";

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator PdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		//credit card details and make payments
		
		LOGGER.info("inside bookFlight() ");
		LOGGER.info("Fetching flight for flight id  "+ request.getFlightId());
		Flight flight = flightRepository.findById(request.getFlightId()).get();
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		
		LOGGER.info("Saving the passenger :" +passenger );
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR+savedReservation.getId()
		+".pdf";
		
		LOGGER.info("Generating the itinerary");
		PdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Sending the itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		
		return savedReservation;
	}

}
