package io.nji.course.service;

import io.nji.course.dto.CourseDTO;
import io.nji.course.mapper.CourseMapper;
import io.nji.course.model.Course;
import io.nji.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDTOList(courses);
    }

    public CourseDTO getCourseById(Long courseId) {
        logger.info("Getting course with id: {}", courseId);
        Optional<Course> course = courseRepository.findById(courseId);
        CourseDTO courseDTO = course.map(courseMapper::toDTO)
             .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        logger.info("Received course: {}", course);
        return courseDTO;
            }

    public CourseDTO saveCourse(CourseDTO courseDTO) {        
        logger.info("Received course DTO: {}", courseDTO);
        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        logger.info("Saved course");
        return courseMapper.toDTO(savedCourse);
    }

    public void deleteCourse(Long courseId) {
        logger.info("Deleting course with id: {}", courseId);
        courseRepository.deleteById(courseId);
        logger.info("Deleted course");
    }

}