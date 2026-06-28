package org.barber.barber_backend.service;

import lombok.RequiredArgsConstructor;
import org.barber.barber_backend.dto.BookingRequestDTO;
import org.barber.barber_backend.model.Booking;
import org.barber.barber_backend.model.Shop;
import org.barber.barber_backend.model.utils.BookedService;
import org.barber.barber_backend.model.utils.OfferedService;
import org.barber.barber_backend.repository.BookingRepository;
import org.barber.barber_backend.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShopRepository shopRepository;

    public Booking createBooking(BookingRequestDTO request) {
        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new IllegalArgumentException("Shop not found."));

        // Create a map of the services offered by the shop for quick lookup
        Map<String, OfferedService> offeredServicesMap = shop.getOfferedServices().stream()
                .collect(Collectors.toMap(OfferedService::getServiceId, Function.identity()));

        // Validate all requested services are offered by the shop
        for (String serviceId : request.getServiceIds()) {
            if (!offeredServicesMap.containsKey(serviceId)) {
                throw new IllegalArgumentException("Service with id " + serviceId + " not offered by this shop.");
            }
        }

        // Create the list of BookedService objects
        List<BookedService> bookedServices = request.getServiceIds().stream()
                .map(serviceId -> BookedService.builder().serviceId(serviceId).build())
                .collect(Collectors.toList());

        if (bookedServices.isEmpty()) {
            throw new IllegalArgumentException("No valid services selected.");
        }

        // Calculate totals from the shop's offered services
        double totalPrice = request.getServiceIds().stream()
                .mapToDouble(id -> offeredServicesMap.get(id).getPrice())
                .sum();
        int totalDuration = request.getServiceIds().stream()
                .mapToInt(id -> offeredServicesMap.get(id).getDurationInMinutes())
                .sum();

        LocalDateTime startTime = request.getAppointmentDateTime();
        LocalDateTime endTime = startTime.plusMinutes(totalDuration);

        // Check for booking conflicts
        List<Booking> conflicts = bookingRepository.findOverlappingBookings(
                request.getShopId(), startTime, endTime
        );

        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("The selected time slot is already booked.");
        }

        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .shopId(request.getShopId())
                .bookedServices(bookedServices)
                .appointmentDateTime(startTime)
                .totalPrice(totalPrice)
                .totalDurationInMinutes(totalDuration)
                .build(); // Status defaults to PENDING

        return bookingRepository.save(booking);
    }
}
