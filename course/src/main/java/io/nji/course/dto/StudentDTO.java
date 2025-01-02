package io.nji.course.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private Long studentId;
    private String name;
    private String email;
    private int skillLevel;

}
