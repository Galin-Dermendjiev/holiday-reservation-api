package com.example.holidayReservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.holidayReservation.DTO.requests.CreateLocationDTO;
import com.example.holidayReservation.DTO.requests.UpdateLocationDTO;
import com.example.holidayReservation.DTO.responses.ResponseLocationDTO;
import com.example.holidayReservation.dao.LocationRepository;
import com.example.holidayReservation.model.Location;
import com.example.holidayReservation.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	private final LocationRepository locationRepository;
	
	public LocationServiceImpl(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Override
	public List<ResponseLocationDTO> getAllLocations() {
		return locationRepository.findAll().stream().map(this::mapToResponseLocationDTO).toList();
	}

	@Override
	public ResponseLocationDTO getLocationById(long id) {
		Location location = locationRepository.findById(id).get();
		return mapToResponseLocationDTO(location);
	}

	@Override
	public ResponseLocationDTO createLocation(CreateLocationDTO newLocation) {
		Location locationToCreate = mapToLocationEntity(newLocation);
		locationRepository.save(locationToCreate); 
		return mapToResponseLocationDTO(locationToCreate);
	}

	@Override
	public ResponseLocationDTO updateLocation(UpdateLocationDTO location) {
		Location locationInDB = locationRepository.findById(location.getId()).get();
		updateLocation(locationInDB, location);
		return mapToResponseLocationDTO(locationInDB);
	}

	private void updateLocation(Location locationInDB, UpdateLocationDTO location) {
		locationInDB.setId(location.getId());
		locationInDB.setCountry(location.getCountry());
		locationInDB.setCity(location.getCity());
		locationInDB.setStreet(location.getStreet());
		locationInDB.setNumber(location.getNumber());
		locationInDB.setImageUrl(location.getImageUrl());
		locationRepository.save(locationInDB);
	}

	@Override
	public boolean deleteLocation(long id) {
		Location location = locationRepository.findById(id).orElse(null);
		if(location != null) {
			locationRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public ResponseLocationDTO mapToResponseLocationDTO(Location location) {
		return new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(), location.getCity(), location.getCountry(), location.getImageUrl());
	}
	
	public Location mapToLocationEntity(CreateLocationDTO dto) {
		return new Location(dto.getCountry(), dto.getCity(), dto.getStreet(), dto.getNumber(), dto.getImageUrl());
	}
	

}
