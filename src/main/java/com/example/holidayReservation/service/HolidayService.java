package com.example.holidayReservation.service;

import java.time.LocalDate;
import java.util.List;

import com.example.holidayReservation.DTO.requests.CreateHolidayDTO;
import com.example.holidayReservation.DTO.requests.UpdateHolidayDTO;
import com.example.holidayReservation.DTO.responses.ResponseHolidayDTO;

public interface HolidayService {
	List<ResponseHolidayDTO> getAllHolidays();
	ResponseHolidayDTO getHolidayById(long id);
	ResponseHolidayDTO createHoliday(CreateHolidayDTO newHoliday);
	ResponseHolidayDTO updateHoliday(UpdateHolidayDTO holiday);
	boolean deleteHoliday(long id);
	
	List<ResponseHolidayDTO> getAllHolidaysByPredicate(String place, LocalDate startDate, Integer duration);
}
