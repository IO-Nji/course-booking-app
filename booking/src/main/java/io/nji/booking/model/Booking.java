package io.nji.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Booking")
@Table(
    name = "booking",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"studentId", "courseId"}
        )
    }
)
@Data
@NoArgsConstructor
public class Booking {

        @Id
    @SequenceGenerator(
        name = "booking_sequence",
        sequenceName = "booking_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "booking_sequence"
    )
    @Column(name = "booking_id", updatable = false)
    Long bookingId;
    @Column(name = "student_id", nullable = false)
    Long studentId;
    @Column(name = "course_id", nullable = false)
    Long courseId;

}
