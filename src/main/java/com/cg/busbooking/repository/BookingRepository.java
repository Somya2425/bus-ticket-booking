package com.cg.busbooking.repository;

import com.cg.busbooking.dto.response.CityTrafficResponseDto;
import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.enums.Status;
import org.springframework.data.domain.Pageable;
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
        SELECT new com.cg.busbooking.dto.response.CityTrafficResponseDto(r.fromCity, COUNT(b))
        FROM Booking b
        JOIN b.trip t
        JOIN t.route r
        WHERE b.status = 'Booked'
        GROUP BY r.fromCity
        ORDER BY COUNT(b) DESC
        """)
    List<CityTrafficResponseDto> getCityTraffic(Pageable pageable);
}
