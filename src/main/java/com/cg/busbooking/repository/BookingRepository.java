package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("select count(b) from Booking b where b.trip.tripId = :tripId and b.status = :status")
    long countByTripIdAndStatus(@Param("tripId") Integer tripId, @Param("status") Status status);

    @Query("SELECT b FROM Booking b " +
            "JOIN Payment p ON b.bookingId = p.booking.bookingId " +
            "WHERE p.customer.customerId = :customerId")
    List<Booking> findBookingsByCustomerId(Integer customerId);

    @Query("""
        SELECT t.route
        FROM Booking b
        JOIN b.trip t
        GROUP BY t.route
        HAVING COUNT(b) >= ALL (
            SELECT COUNT(b2)
            FROM Booking b2
            JOIN b2.trip t2
            GROUP BY t2.route
        )
    """)
    List<Route> findMostPopularRoutes();
}
