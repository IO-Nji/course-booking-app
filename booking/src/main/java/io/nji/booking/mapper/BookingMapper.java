package io.nji.booking.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.nji.booking.dto.BookingDTO;
import io.nji.booking.model.Booking;

@Component
public class BookingMapper {

    // Convert Booking Entity to Booking DTO
    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setStudentId(booking.getStudentId());
        dto.setCourseId(booking.getCourseId());
        return dto;
    }

    // Convert Booking DTO to Booking Entity
    public Booking toEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setStudentId(dto.getStudentId());
        booking.setCourseId(dto.getCourseId());
        return booking;
    }

    // Convert a list of Booking entities to a list of Booking DTOs
    public List<BookingDTO> toDTOList(List<Booking> bookings) {
        return bookings.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
