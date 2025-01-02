package io.nji.student.controller;

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

import io.nji.student.dto.CourseDTO;
import io.nji.student.dto.StudentDTO;
import io.nji.student.service.StudentService;
import io.nji.student.validator.StudentValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentValidator studentValidator;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class); 

    @GetMapping("/getAll")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/getById/{id}")
    public StudentDTO getStudentById(@PathVariable("id") Long studentId) {
        logger.info("Getting student with id: {}", studentId);
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        logger.info("Received student: {}", studentDTO);
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult) {
        logger.info("Received student DTO: {}", studentDTO);
        if (bindingResult.hasErrors()) {
            return studentValidator.handleValidationExceptions(bindingResult);
        }
        StudentDTO savedStudent = studentService.saveStudent(studentDTO);
        logger.info("Saved student");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
    }

     @PutMapping("/modify/{id}")
    public StudentDTO updateStudent(@PathVariable("id") Long studentId, @Valid @RequestBody StudentDTO studentDTO) {
        logger.info("Updating student with id: {}", studentId);
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDTO);
        logger.info("Updated student");
        return updatedStudent;
    }

        // View All Courses
   @GetMapping("/viewCourses")
   public List<CourseDTO> viewAllCourses() {
       return studentService.viewAllCourses();
   }

   @GetMapping("getCourseById/{id}")
   public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
       return studentService.viewCourseById(courseId);
   }
   public String getMethodName(@RequestParam String param) {
       return new String();
   }
   


}
