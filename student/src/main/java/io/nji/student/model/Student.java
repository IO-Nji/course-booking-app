package io.nji.student.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Student")
@Table(
    name = "student",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"email"}
        )
    }
)
@Data
@NoArgsConstructor
public class Student {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    @Column(name = "student_id", updatable = false)
    private Long studentId;
    
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "skillLevel", nullable = false)
    private int skillLevel;

}
