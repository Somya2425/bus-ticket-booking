package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("select count(b) from Booking b where b.trip.tripId = :tripId and b.status = :status")
    long countByTripIdAndStatus(@Param("tripId") Integer tripId, @Param("status") Status status);
}
