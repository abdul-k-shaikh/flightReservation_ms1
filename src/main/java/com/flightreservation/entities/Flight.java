package com.flightreservation.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
@Entity
@Table(name = "flight")
public class Flight implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FLIGHT_NUMBER")
	private String flightNumber;
	
	@Column(name = "OPERATING_AIRLINES")
	private String operatingAirlines;
	
	@Column(name = "DEPARTURE_CITY")
	private String departureCity;
	
	@Column(name = "ARRIVAL_CITY")
	private String arrivalCity;
	
	@Column(name = "DATE_OF_DEPARTURE")
	private Date dateOfDeparture;
	
	@Column(name = "ESTIMATED_DEPARTURE_TIME")
	private Timestamp estimatedDepartureTime;
	
}
