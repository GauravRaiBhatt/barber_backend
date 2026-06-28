package org.barber.barber_backend.repository;

import org.barber.barber_backend.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    /**
     * Finds bookings that overlap with a given time range for a specific shop.
     * A booking overlaps if its start time is before the new booking's end time,
     * AND its end time is after the new booking's start time.
     */
    @Query("{ 'shopId': ?0, 'appointmentDateTime': { '$lt': ?2 }, 'status': { '$ne': 'CANCELLED' }, '$expr': { '$gt': [ { '$add': [ '$appointmentDateTime', { '$multiply': [ '$totalDurationInMinutes', 60000 ] } ] }, ?1 ] } }")
    List<Booking> findOverlappingBookings(String shopId, LocalDateTime newBookingStart, LocalDateTime newBookingEnd);
}
