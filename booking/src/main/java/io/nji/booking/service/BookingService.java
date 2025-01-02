package io.nji.booking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nji.booking.dto.BookingDTO;
import io.nji.booking.mapper.BookingMapper;
import io.nji.booking.model.Booking;
import io.nji.booking.repository.BookingRepository;

@Service
public class BookingService {

        private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

        @Autowired
        private BookingRepository bookingRepository;

        @Autowired
        private BookingMapper bookingMapper;

        public void saveBooking(BookingDTO bookingDTO) {
                logger.info("Received booking: {}", bookingDTO);
                bookingRepository.save(bookingMapper.toEntity(bookingDTO));
                logger.info("Saved booking: {}", bookingDTO);
        }

        public BookingDTO getBookingById(Long bookingId) {
                logger.info("Getting booking with id: {}", bookingId);
                Optional<Booking> booking = bookingRepository.findById(bookingId);
                BookingDTO bookingDTO = booking.map(bookingMapper::toDTO)
                        .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
                logger.info("Received booking: {}", bookingDTO);
                return bookingDTO;
        }

        public List<BookingDTO> getAllBookings() {
                logger.info("Getting all bookings");
                List<Booking> bookings = bookingRepository.findAll();
                List<BookingDTO> bookingDTOs = bookingMapper.toDTOList(bookings);
                logger.info("Received bookings: {}", bookingDTOs);
                return bookingDTOs;
        }

        public void deleteBooking(Long bookingId) {
                logger.info("Deleting booking with id: {}", bookingId);
                bookingRepository.deleteById(bookingId);
                logger.info("Deleted booking");
        }

}
