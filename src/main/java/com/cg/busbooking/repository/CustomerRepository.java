package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT c FROM Customer c JOIN c.address a " +
            "WHERE c.name = :name AND a.address = :address")
    List<Customer> findByNameAndAddress( @Param("name") String name,
                                         @Param("address") String address);

    @Query("SELECT DISTINCT c FROM Customer c " +
            "JOIN Payment p ON c.customerId = p.customer.customerId " +
            "JOIN Booking b ON p.booking.bookingId = b.bookingId " +
            "JOIN Trip t ON b.trip.tripId = t.tripId " +
            "JOIN Bus bus ON t.bus.busId = bus.busId " +
            "JOIN AgencyOffice ao ON bus.office.officeId = ao.officeId " +
            "WHERE ao.agency.agencyId = :agencyId")
    List<Customer> findCustomersByAgency(Integer agencyId);
}
