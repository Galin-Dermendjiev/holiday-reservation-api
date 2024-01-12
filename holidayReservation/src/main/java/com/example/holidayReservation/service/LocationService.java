package com.example.holidayReservation.service;

import java.util.List;

import com.example.holidayReservation.DTO.requests.CreateLocationDTO;
import com.example.holidayReservation.DTO.requests.UpdateLocationDTO;
import com.example.holidayReservation.DTO.responses.ResponseLocationDTO;

public interface LocationService {
	List<ResponseLocationDTO> getAllLocations();
	ResponseLocationDTO getLocationById(long id);
	ResponseLocationDTO createLocation(CreateLocationDTO newLocation);
	ResponseLocationDTO updateLocation(UpdateLocationDTO location);
	boolean deleteLocation(long id);
}
