package com.example.holidayReservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.holidayReservation.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
