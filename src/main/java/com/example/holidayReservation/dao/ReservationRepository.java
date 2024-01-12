package com.example.holidayReservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.holidayReservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
