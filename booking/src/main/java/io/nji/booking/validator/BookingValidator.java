package io.nji.booking.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.nji.booking.dto.BookingDTO;

@Component
public class BookingValidator {

        @Autowired
        private RestTemplate restTemplate;

        public void validator(BookingDTO bookingDTO) {
            // Check if the student exists
            String studentServiceUrl = "http://localhost:9050/students/getById/" + bookingDTO.getStudentId();
            try {
                ResponseEntity<Void> studentResponse = restTemplate.getForEntity(studentServiceUrl, Void.class);
                if (!studentResponse.getStatusCode().is2xxSuccessful()) {
                    throw new RuntimeException("Student not found with id: " + bookingDTO.getStudentId());
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Student not found with id: " + bookingDTO.getStudentId());
            }
    
            // Check if the course exists
            String courseServiceUrl = "http://localhost:9040/courses/getById/" + bookingDTO.getCourseId();
            try {
                ResponseEntity<Void> courseResponse = restTemplate.getForEntity(courseServiceUrl, Void.class);
                if (!courseResponse.getStatusCode().is2xxSuccessful()) {
                    throw new RuntimeException("Course not found with id: " + bookingDTO.getCourseId());
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Course not found with id: " + bookingDTO.getCourseId());
            }
        }
    }


