package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT b FROM Booking b " +
            "JOIN Payment p ON b.bookingId = p.booking.bookingId " +
            "WHERE p.customer.customerId = :customerId")
    List<Booking> findBookingsByCustomerId(Integer customerId);
}
