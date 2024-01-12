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

import com.example.holidayReservation.DTO.requests.CreateLocationDTO;
import com.example.holidayReservation.DTO.requests.UpdateLocationDTO;
import com.example.holidayReservation.DTO.responses.ResponseLocationDTO;
import com.example.holidayReservation.service.LocationService;

@RestController
public class LocationController {

	private final LocationService locationService;

	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}
	
	@GetMapping("/locations")
	public ResponseEntity<List<ResponseLocationDTO>> getAll(){
		return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
	}

	@GetMapping("/locations/{locationId}")
	public ResponseEntity<ResponseLocationDTO> getOne(@PathVariable("locationId") long locationId){
		ResponseLocationDTO location = locationService.getLocationById(locationId);
		return new ResponseEntity<>(location, HttpStatus.OK);
	}
	
	@PostMapping("/locations")
	public ResponseEntity<ResponseLocationDTO> create(@RequestBody CreateLocationDTO newLocation){
		ResponseLocationDTO location = locationService.createLocation(newLocation);
		return new ResponseEntity<>(location, HttpStatus.OK);
	}

	@PutMapping("/locations")
	public ResponseEntity<ResponseLocationDTO> update(@RequestBody UpdateLocationDTO location){
		ResponseLocationDTO updatedLocation = locationService.updateLocation(location);
		return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
	}
	
	@DeleteMapping("/locations/{locationId}")
	public ResponseEntity<Boolean> delete(@PathVariable("locationId") long locationId){
		boolean isDeleted = locationService.deleteLocation(locationId);
		if(!isDeleted) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
}
