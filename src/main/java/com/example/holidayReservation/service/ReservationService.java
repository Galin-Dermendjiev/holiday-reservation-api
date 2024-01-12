package com.example.holidayReservation.service;

import java.util.List;

import com.example.holidayReservation.DTO.requests.CreateReservationDTO;
import com.example.holidayReservation.DTO.requests.UpdateReservationDTO;
import com.example.holidayReservation.DTO.responses.ResponseReservationDTO;

public interface ReservationService {
	List<ResponseReservationDTO> getAllReservations();
	ResponseReservationDTO getReservationById(long id);
	ResponseReservationDTO createReservation(CreateReservationDTO newReservation);
	ResponseReservationDTO updateReservation(UpdateReservationDTO reservation);
	boolean deleteReservation(long id);
}
