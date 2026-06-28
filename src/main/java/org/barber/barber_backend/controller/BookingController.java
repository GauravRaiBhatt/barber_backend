package org.barber.barber_backend.controller;

import lombok.RequiredArgsConstructor;
import org.barber.barber_backend.dto.BookingRequestDTO;
import org.barber.barber_backend.model.Booking;
import org.barber.barber_backend.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDTO request) {
        try {
            Booking newBooking = bookingService.createBooking(request);
            return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // It's better to handle exceptions in a centralized @ControllerAdvice
            // but for now, this is fine.
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public String getBookingService() {
        return "Hi Sam , App started :)";
    }

    // You can add other endpoints here later, for example:
    // @GetMapping("/{id}")
    // public ResponseEntity<Booking> getBookingById(@PathVariable String id) { ... }
}
