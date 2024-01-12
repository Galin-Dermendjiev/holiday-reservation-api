package com.example.holidayReservation.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.holidayReservation.DTO.requests.CreateHolidayDTO;
import com.example.holidayReservation.DTO.requests.UpdateHolidayDTO;
import com.example.holidayReservation.DTO.responses.ResponseHolidayDTO;
import com.example.holidayReservation.service.HolidayService;

@RestController
public class HolidayController {

	private final HolidayService holidayService;

	public HolidayController(HolidayService holidayService) {
		super();
		this.holidayService = holidayService;
	}
	
	@GetMapping("/holidays")
	public ResponseEntity<List<ResponseHolidayDTO>> getAll(@RequestParam(name = "duration", required = false) Integer duration,
															@RequestParam(name = "startDate", required = false) LocalDate startDate,
															@RequestParam(name = "location", required = false) String location){

		return new ResponseEntity<>(holidayService.getAllHolidaysByPredicate(location, startDate, duration), HttpStatus.OK);
	}
	
	@GetMapping("/holidays/{holidayId}")
	public ResponseEntity<ResponseHolidayDTO> getOne(@PathVariable("holidayId") long holidayId) {
		ResponseHolidayDTO holiday = holidayService.getHolidayById(holidayId);
		if(holiday == null) {
			return new ResponseEntity<>(holiday, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(holiday, HttpStatus.OK);
	}
	
	@PostMapping("/holidays")
	public ResponseEntity<ResponseHolidayDTO> create(@RequestBody CreateHolidayDTO newHoliday){ 
		ResponseHolidayDTO holiday = holidayService.createHoliday(newHoliday);
		return new ResponseEntity<>(holiday, HttpStatus.OK);
		
	}
	
	@PutMapping("/holidays")
	public ResponseEntity<ResponseHolidayDTO> update(@RequestBody UpdateHolidayDTO holiday){
		ResponseHolidayDTO updatedHoliday = holidayService.updateHoliday(holiday);
		return new ResponseEntity<>(updatedHoliday, HttpStatus.OK);
	}
	
	@DeleteMapping("/holidays/{holidayId}")
	public ResponseEntity<Boolean> delete(@PathVariable("holidayId") long holidayId) {
		boolean isDeleted = holidayService.deleteHoliday(holidayId);
		if(!isDeleted) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
	
}
