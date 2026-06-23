package org.barber.barber_backend.service;

import lombok.RequiredArgsConstructor;
import org.barber.barber_backend.controller.BookingRequest;
import org.barber.barber_backend.model.Booking;
import org.barber.barber_backend.model.BookingStatus;
import org.barber.barber_backend.model.ServiceEntity;
import org.barber.barber_backend.repository.BookingRepository;
import org.barber.barber_backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;

    public Booking createBooking(BookingRequest request) {
        List<ServiceEntity> services = serviceRepository.findAllByIdIn(request.serviceIds());

        if (services.isEmpty()) {
            throw new IllegalArgumentException("No valid services selected.");
        }

        // Validate all services belong to the requested shop
        boolean isValidShop = services.stream().allMatch(s -> s.getShopId().equals(request.shopId()));
        if (!isValidShop) {
            throw new IllegalArgumentException("All selected services must belong to the specified shop.");
        }

        BigDecimal totalPrice = services.stream()
                .map(ServiceEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalDurationMins = services.stream()
                .mapToInt(ServiceEntity::getDurationMins)
                .sum();

        LocalTime endTime = request.startTime().plusMinutes(totalDurationMins);

        // Conflict check using MongoDB query
        List<Booking> conflicts = bookingRepository.findOverlappingBookings(
                request.shopId(), request.bookingDate(), request.startTime(), endTime
        );

        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("The selected time slot is already booked.");
        }

        Booking booking = Booking.builder()
                .userId(request.userId())
                .shopId(request.shopId())
                .bookingDate(request.bookingDate())
                .startTime(request.startTime())
                .endTime(endTime)
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .serviceIds(request.serviceIds())
                .build();

        return bookingRepository.save(booking);
    }
}