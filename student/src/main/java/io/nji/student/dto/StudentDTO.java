package io.nji.student.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StudentDTO {

    @Valid
    @Column(name = "student_id", updatable = false)
    private Long studentId;
    
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull(message = "SkillLevel is mandatory")
    private int skillLevel;

}
