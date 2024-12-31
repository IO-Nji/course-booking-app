package io.nji.course.controller;

import io.nji.course.dto.CourseDTO;
import io.nji.course.service.CourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @GetMapping("/getAll")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/getById/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
        logger.info("Getting course with id: {}", courseId);
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        logger.info("Received course: {}", courseDTO);
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/add")
    public CourseDTO saveCourse(@RequestBody CourseDTO CourseDTO) {
        CourseDTO savedCourse = courseService.saveCourse(CourseDTO);
        logger.info("Saved course");
        return savedCourse;
    }

    @DeleteMapping("/remove/{id}")
    public void deleteCourse(@PathVariable("id") Long courseId) {
        logger.info("Deleting course with id: {}", courseId);
        courseService.deleteCourse(courseId);
        logger.info("Deleted course");
       
    }

}
