package io.nji.course.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long courseId;
    private String title;
    private String description;
    private int skillLevel;

}
