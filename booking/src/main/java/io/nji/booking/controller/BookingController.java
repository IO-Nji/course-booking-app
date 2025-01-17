package io.nji.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nji.booking.dto.BookingDTO;
import io.nji.booking.mapper.BookingMapper;
import io.nji.booking.repository.BookingRepository;
import io.nji.booking.service.BookingService;
import io.nji.booking.validator.BookingValidator;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingValidator bookingValidator;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private BookingRepository bookingRepository;

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
    public void saveBooking(BookingDTO bookingDTO) {
        logger.info("Received booking: {}", bookingDTO);
        bookingValidator.validator(bookingDTO);
        bookingRepository.save(bookingMapper.toEntity(bookingDTO));
        logger.info("Saved booking: {}", bookingDTO);
    } 

    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable("id") Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
