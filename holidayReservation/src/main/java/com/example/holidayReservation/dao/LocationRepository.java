package com.example.holidayReservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.holidayReservation.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
