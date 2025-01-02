package io.nji.course.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CourseDTO {

    @Valid
    @Column(name = "course_id", updatable = false)
    private Long courseId;
    
    @NotNull(message = "Title is mandatory")
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "SkillLevel is mandatory")
    private int skillLevel;

}
