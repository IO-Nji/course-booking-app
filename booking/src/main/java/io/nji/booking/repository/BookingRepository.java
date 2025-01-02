package io.nji.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nji.booking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}