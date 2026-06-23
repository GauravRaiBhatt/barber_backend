package org.barber.barber_backend.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record BookingRequest(
        @NotNull(message = "User ID is required") String userId,
        @NotNull(message = "Shop ID is required") String shopId,
        @NotEmpty(message = "At least one service must be selected") List<String> serviceIds,
        @NotNull(message = "Booking date is required") LocalDate bookingDate,
        @NotNull(message = "Start time is required") LocalTime startTime
) {}