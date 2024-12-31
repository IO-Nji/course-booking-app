package io.nji.course.mapper;

import io.nji.course.dto.CourseDTO;
import io.nji.course.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    // Convert Course entity to CourseDTO
    public CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseId(course.getCourseId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setSkillLevel(course.getSkillLevel());
        return dto;
    }

    // Convert CourseDTO to Course entity
    public Course toEntity(CourseDTO dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setSkillLevel(dto.getSkillLevel());
        return course;
    }

    // Convert a list of Course entities to a list of CourseDTOs
    public List<CourseDTO> toDTOList(List<Course> courses) {
        return courses.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
