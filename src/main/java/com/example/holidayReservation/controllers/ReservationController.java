package com.example.holidayReservation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.holidayReservation.DTO.requests.CreateReservationDTO;
import com.example.holidayReservation.DTO.requests.UpdateReservationDTO;
import com.example.holidayReservation.DTO.responses.ResponseReservationDTO;
import com.example.holidayReservation.service.ReservationService;

@RestController
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}
	
	@GetMapping("/reservations")
	public ResponseEntity<List<ResponseReservationDTO>> getAll(){
		return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
	}

	@GetMapping("/reservations/{reservationId}")
	public ResponseEntity<ResponseReservationDTO> getOne(@PathVariable("reservationId") long reservationId){
		ResponseReservationDTO reservation = reservationService.getReservationById(reservationId);
		if(reservation == null) {
			return new ResponseEntity<>(reservation, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}

	@PostMapping("/reservations")
	public ResponseEntity<ResponseReservationDTO> create(@RequestBody CreateReservationDTO newReservation){
		ResponseReservationDTO reservation = reservationService.createReservation(newReservation);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@PutMapping("/reservations")
	public ResponseEntity<ResponseReservationDTO> update(@RequestBody UpdateReservationDTO reservation){
		ResponseReservationDTO updatedReservation = reservationService.updateReservation(reservation);
		return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
	}
	
	@DeleteMapping("/reservations/{reservationId}")
	public ResponseEntity<Boolean> delete(@PathVariable("reservationId") long reservationId){
		boolean isDeleted = reservationService.deleteReservation(reservationId);
		if(!isDeleted) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
}
