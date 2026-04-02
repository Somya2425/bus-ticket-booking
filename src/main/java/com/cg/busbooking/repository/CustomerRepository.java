package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing database operations on Customer entity.
 * This interface provides methods to:
 * - Fetch customers based on agency ID
 * - Fetch customers based on name and address
 * It extends JpaRepository to provide basic CRUD operations.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    /**
     * Retrieves customers associated with a specific agency ID.
     * This query joins multiple entities like Payment, Booking, Trip, Bus,
     * and Agency Office to fetch distinct customers belonging to an agency.
     * @param agencyId the ID of the agency
     * @return list of Customer entities associated with the agency
     */
    @Query("""

       SELECT DISTINCT p.customer FROM Payment p
       JOIN p.booking b
       JOIN b.trip t
       JOIN t.bus bs
       JOIN bs.office ao
       WHERE ao.agency.agencyId = :agencyId
       """)
    List<Customer> findCustomerByAgencyId(Integer agencyId);

    /**
     * Retrieves customers based on their name and address.
     * This query joins Customer and Address entities to filter
     * customers matching the given name and address.
     * @param name the name of the customer
     * @param address the address of the customer
     * @return list of Customer entities matching the criteria
     */
    @Query("SELECT c FROM Customer c JOIN c.address a " +
            "WHERE c.name = :name AND a.address = :address")
    List<Customer> findByNameAndAddress( @Param("name") String name,
                                         @Param("address") String address);


}
