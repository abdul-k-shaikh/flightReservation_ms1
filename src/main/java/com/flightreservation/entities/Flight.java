package com.flightreservation.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
//@Table(name = "flight")
public class Flight {
	
//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "FLIGHT_NUMBER")
	private String flightNumber;
	
//	@Column(name = "OPERATING_AIRLINES")
	private String operatingAirlines;
	
//	@Column(name = "DEPARTURE_CITY")
	private String departureCity;
	
//	@Column(name = "ARRIVAL_CITY")
	private String arrivalCity;
	
//	@Column(name = "DATE_OF_DEPARTURE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfDeparture;
	
//	@Column(name = "ESTIMATED_DEPARTURE_TIME")
	private Timestamp estimatedDepartureTime;
	
}
