package io.nji.booking.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookingDTO {
@Valid
    @Column(name = "booking_id", updatable = false)
    private Long bookingId;
    @NotNull(message = "Student ID is mandatory")
    private Long studentId;
    @NotNull(message = "Course ID is mandatory")
    private Long courseId;

}
