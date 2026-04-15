package com.aditi.transitflow.repository;

import com.aditi.transitflow.entity.Booking;
import com.aditi.transitflow.entity.Route;
import com.aditi.transitflow.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing database operations on Booking entity.
 * This interface provides methods to:
 * - Count bookings based on trip ID and status
 * - Fetch bookings for a specific customer
 * - Retrieve most popular routes based on booking count
 * It extends JpaRepository to provide basic CRUD operations.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    /**
     * Counts the number of bookings for a given trip ID and status.
     * @param tripId the ID of the trip
     * @param status the booking status (e.g., CONFIRMED, CANCELLED)
     * @return total number of bookings matching the criteria
     */
    @Query("select count(b) from Booking b where b.trip.tripId = :tripId and b.status = :status")
    long countByTripIdAndStatus(@Param("tripId") Integer tripId, @Param("status") Status status);

    /**
     * Retrieves all bookings associated with a specific customer ID.
     * This query joins Booking and Payment entities to fetch bookings
     * made by a particular customer.
     * @param customerId the ID of the customer
     * @return list of Booking entities for the given customer
     */
    @Query("SELECT b FROM Booking b " +
            "JOIN Payment p ON b.bookingId = p.booking.bookingId " +
            "WHERE p.customer.customerId = :customerId")
    List<Booking> findBookingsByCustomerId(Integer customerId);

    /**
     * Retrieves the most popular routes based on booking count.
     * This query finds routes with the highest number of bookings
     * by comparing counts across all routes.
     * @return list of Route entities representing most popular routes
     */
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
