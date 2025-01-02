package io.nji.course.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nji.course.dto.CourseDTO;
import io.nji.course.service.CourseService;
import io.nji.course.validator.CourseValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController { 

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

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
    public ResponseEntity<?> saveCourse(@Valid @RequestBody CourseDTO courseDTO, BindingResult bindingResult) {
        logger.info("Received course DTO: {}", courseDTO);
        if (bindingResult.hasErrors()) {
            return courseValidator.handleValidationExceptions(bindingResult);
        }
        CourseDTO savedCourse = courseService.saveCourse(courseDTO);
        logger.info("Saved course");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }


    @DeleteMapping("/remove/{id}")
    public void deleteCourse(@PathVariable("id") Long courseId) {
        logger.info("Deleting course with id: {}", courseId);
        courseService.deleteCourse(courseId);
        logger.info("Deleted course");
       
    }

    @PutMapping("/modify/{id}")
    public CourseDTO modifyCourse(@PathVariable("id") Long courseId, @Valid @RequestBody CourseDTO courseDTO) {
        logger.info("Updating course with id: {}", courseId);
        CourseDTO updatedCourse = courseService.updateCourse(courseId, courseDTO);
        logger.info("Updated course");
        return updatedCourse;
    }   


}
