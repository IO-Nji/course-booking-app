package io.nji.course.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Course")
@Table(
    name = "course",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"title"}
        )
    }
)
@Data
@NoArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(
        name = "course_sequence",
        sequenceName = "course_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_sequence"
    )
    @Column(name = "course_id", updatable = false)
    private Long courseId;
    
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "skillLevel", nullable = false)
    private int skillLevel;
} 
