package com.example.holidayReservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.holidayReservation.DTO.requests.CreateReservationDTO;
import com.example.holidayReservation.DTO.requests.UpdateReservationDTO;
import com.example.holidayReservation.DTO.responses.ResponseHolidayDTO;
import com.example.holidayReservation.DTO.responses.ResponseReservationDTO;
import com.example.holidayReservation.dao.HolidayRepository;
import com.example.holidayReservation.dao.ReservationRepository;
import com.example.holidayReservation.model.Holiday;
import com.example.holidayReservation.model.Reservation;
import com.example.holidayReservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final HolidayServiceImpl holidayService;
	private final ReservationRepository reservationRepository;
	private final HolidayRepository holidayRepository;

	public ReservationServiceImpl(ReservationRepository reservationRepository, HolidayRepository holidayRepository, HolidayServiceImpl holidayService) {
		super();
		this.reservationRepository = reservationRepository;
		this.holidayRepository = holidayRepository;
		this.holidayService = holidayService;
	}

	@Override
	public List<ResponseReservationDTO> getAllReservations() {
		return reservationRepository.findAll().stream().map(this::mapToResponseReservationDTO).toList();
	}

	@Override
	public ResponseReservationDTO getReservationById(long id) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		if(reservation == null) {
			return null;
		}
		return mapToResponseReservationDTO(reservation);
	}

	@Override
	public ResponseReservationDTO createReservation(CreateReservationDTO newReservation) {
		Reservation reservationToCreate = mapToReservationEntity(newReservation);
		reservationRepository.save(reservationToCreate);
		return mapToResponseReservationDTO(reservationToCreate);
	}

	@Override
	public ResponseReservationDTO updateReservation(UpdateReservationDTO reservation) {
		Reservation reservationInDB = reservationRepository.findById(reservation.getId()).get();
		updateReservation(reservationInDB, reservation);
		return null;
	}

	private void updateReservation(Reservation reservationInDB, UpdateReservationDTO reservation) {
		Holiday holiday = holidayRepository.findById(reservation.getHoliday()).get();
		reservationInDB.setId(reservation.getId());
		reservationInDB.setContactName(reservation.getContactName());
		reservationInDB.setPhoneNumber(reservation.getPhoneNumber());
		reservationInDB.setHoliday(holiday);
		reservationRepository.save(reservationInDB);
	}

	@Override
	public boolean deleteReservation(long id) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		if(reservation != null) {
			reservationRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public ResponseReservationDTO mapToResponseReservationDTO(Reservation reservation) {
		Holiday holiday = holidayRepository.findById(reservation.getHoliday().getId()).get();
		ResponseHolidayDTO holidayDTO = holidayService.mapToResponseHolidayDTO(holiday);
		return new ResponseReservationDTO(reservation.getId(), reservation.getContactName(), reservation.getPhoneNumber(), holidayDTO);
	}

	public Reservation mapToReservationEntity(CreateReservationDTO dto) {
		Holiday holiday = holidayRepository.findById(dto.getHoliday()).get();
		return new Reservation(dto.getPhoneNumber(), dto.getContactName(), holiday);
	} 
	
	
}
