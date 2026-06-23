package org.barber.barber_backend.controller;

import java.time.LocalTime;

public record BookingResponse(
        String bookingId,
        String status,
        String totalPrice,
        LocalTime startTime,
        LocalTime endTime
) {}