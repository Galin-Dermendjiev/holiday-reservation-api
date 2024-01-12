package com.example.holidayReservation.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.example.holidayReservation.DTO.requests.CreateHolidayDTO;
import com.example.holidayReservation.DTO.requests.UpdateHolidayDTO;
import com.example.holidayReservation.DTO.responses.ResponseHolidayDTO;
import com.example.holidayReservation.DTO.responses.ResponseLocationDTO;
import com.example.holidayReservation.DTO.responses.ResponseReservationDTO;
import com.example.holidayReservation.dao.HolidayRepository;
import com.example.holidayReservation.dao.LocationRepository;
import com.example.holidayReservation.model.Holiday;
import com.example.holidayReservation.model.Location;
import com.example.holidayReservation.model.Reservation;
import com.example.holidayReservation.service.HolidayService;

import jakarta.validation.Valid;

@Service
public class HolidayServiceImpl implements HolidayService {

	private final HolidayRepository holidayRepository;
	private final LocationRepository locationRepository;

	public HolidayServiceImpl(HolidayRepository holidayRepository, LocationRepository locationRepository) {
		this.holidayRepository = holidayRepository;
		this.locationRepository = locationRepository;
	}

	@Override
	public List<ResponseHolidayDTO> getAllHolidays() {
		return holidayRepository.findAll().stream().map(this::mapToResponseHolidayDTO).toList();
	}
	
	// Use predicates to filter holidays
	@Override
	public List<ResponseHolidayDTO> getAllHolidaysByPredicate(String place, LocalDate startDate, Integer duration) {
		Predicate<? super Holiday> location = place != null ?
		        x -> x.getLocation().getCity().toLowerCase().equals(place.toLowerCase()) 
		        				|| x.getLocation().getCountry().toLowerCase().equals(place.toLowerCase()) :
		        x -> true; 
 
		Predicate<? super Holiday> date = startDate != null ?
		        x -> x.getStartDate().isEqual(startDate) :
		        x -> true; 

		Predicate<? super Holiday> days = duration != null ?
		        x -> x.getDuration() == duration :
		        x -> true; 
		
		return holidayRepository.findAll().stream()
				.filter(h -> location.test(h))
			    .filter(h -> date.test(h))
			    .filter(h -> days.test(h))
			    .map(this::mapToResponseHolidayDTO)
			    .toList();

	}

	@Override
	public ResponseHolidayDTO getHolidayById(long id) {
		Holiday holiday = holidayRepository.findById(id).orElse(null);
		if(holiday == null)
			return null;
		return mapToResponseHolidayDTO(holiday);
	}

	@Override
	public ResponseHolidayDTO createHoliday (CreateHolidayDTO newHoliday) {
		Holiday holidayToSave = mapToHolidayEntity(newHoliday);
		holidayRepository.save(holidayToSave);
		return mapToResponseHolidayDTO(holidayToSave);
	}

	@Override
	public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO holiday) {
		Holiday holidayInDB = holidayRepository.findById(holiday.getId()).get();
		updateHoliday(holidayInDB, holiday);
		return mapToResponseHolidayDTO(holidayInDB);
	}

	@Override
	public boolean deleteHoliday(long id) {
		Holiday holiday = holidayRepository.findById(id).orElse(null);
		if (holiday != null) {
			holidayRepository.deleteById(id);
			return true;
		}
		return false;
		
	}

	private void updateHoliday(Holiday holidayInDB, UpdateHolidayDTO holiday) {
		Location location = locationRepository.findById(holiday.getLocation()).get();

		holidayInDB.setDuration(holiday.getDuration());
		holidayInDB.setFreeSlots(holiday.getFreeSlots());
		holidayInDB.setId(holiday.getId());
		holidayInDB.setLocation(location);
		holidayInDB.setPrice(Double.parseDouble(holiday.getPrice()));
		holidayInDB.setStartDate(holiday.getStartDate());
		holidayInDB.setTitle(holiday.getTitle());
		holidayRepository.save(holidayInDB);
	}

	
	
	public ResponseHolidayDTO mapToResponseHolidayDTO(Holiday holiday) {
		ResponseLocationDTO location = mapToResponseLocationDTO(holiday.getLocation());
		return new ResponseHolidayDTO(holiday.getId(), location, holiday.getTitle(), holiday.getStartDate(),
				holiday.getDuration(), holiday.getPrice(), holiday.getFreeSlots());
	}

	public ResponseLocationDTO mapToResponseLocationDTO(Location location) {
		return new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(), location.getCity(),
				location.getCountry(), location.getImageUrl());
	}

	public ResponseReservationDTO mapToResponseReservationDTO(Reservation reservation) {
		ResponseHolidayDTO holiday = mapToResponseHolidayDTO(reservation.getHoliday());
		return new ResponseReservationDTO(reservation.getId(), reservation.getContactName(),
				reservation.getPhoneNumber(), holiday);
	}

	public Holiday mapToHolidayEntity(CreateHolidayDTO holiday) {
		Location location = locationRepository.findById(holiday.getLocation()).orElse(null);
		if(location == null) {
			return null;
		}
		return new Holiday(holiday.getTitle(), holiday.getDuration(), holiday.getFreeSlots(),
				Double.parseDouble(holiday.getPrice()), holiday.getStartDate(), location);
	}


}
