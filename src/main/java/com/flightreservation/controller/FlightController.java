package com.flightreservation.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.entities.Flight;
import com.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class); 

	@GetMapping("findFlights")
	public String showFindFlights() {
		return "findFlights";
	}

//	@DateTimeFormat(pattern = "MM-dd-yyyy") 
	@PostMapping("findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
			ModelMap modelMap) {
		LOGGER.info("Inside findFlights() From: "+from+"TO: "+to+"Departure Date: "+ departureDate);
//		java.sql.Date sqlDate = new java.sql.Date(departureDate.getTime());
		List<Flight> flights = flightRepository.findflights(from,to,departureDate);
		System.out.println("flight details are"+ flights);
		LOGGER.info("Flights Found are "+ flights);
		modelMap.addAttribute("flights", flights);
		return "displayFlights";
	}
}
