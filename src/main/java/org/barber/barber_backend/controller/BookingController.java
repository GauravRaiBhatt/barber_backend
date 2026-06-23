package org.barber.barber_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public String getBookingService() {
        return "Hi Sam , App started :)" ;
    }

//    Ambiguous mapping will not work
//    @GetMapping
//    public String second() {
//        return "second method" ;
//    }

    @PostMapping
    public ResponseEntity<?> checkoutCartAndBook(@Valid @RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService.createBooking(request);
            BookingResponse response = new BookingResponse(
                    booking.getId(),
                    booking.getStatus().name(),
                    booking.getTotalPrice().toString(),
                    booking.getStartTime(),
                    booking.getEndTime()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException | IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}