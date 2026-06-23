package org.barber.barber_backend.repository;

import org.barber.barber_backend.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    // MongoDB JSON Query for checking overlapping time slots
    @Query("{ 'shopId': ?0, 'bookingDate': ?1, 'status': { $ne: 'CANCELLED' }, " +
            "'startTime': { $lt: ?3 }, 'endTime': { $gt: ?2 } }")
    List<Booking> findOverlappingBookings(String shopId, LocalDate date, LocalTime startTime, LocalTime endTime);
}