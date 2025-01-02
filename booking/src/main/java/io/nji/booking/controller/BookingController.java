package io.nji.booking.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nji.booking.dto.BookingDTO;
import io.nji.booking.service.BookingService;
import io.nji.booking.validator.BookingValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingValidator bookingValidator;

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @GetMapping("/getAll")
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/getById/{id}")
    public BookingDTO getBookingById(@PathVariable("id") Long bookingId) {
        logger.info("Getting booking with id: {}", bookingId);
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        logger.info("Received booking: {}", bookingDTO);
        return bookingService.getBookingById(bookingId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveBooking(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bookingValidator.handleValidationExceptions(bindingResult);
        }
        bookingService.saveBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }   

    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable("id") Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
