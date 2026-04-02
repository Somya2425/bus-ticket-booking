package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("""

       SELECT DISTINCT p.customer FROM Payment p
       JOIN p.booking b
       JOIN b.trip t
       JOIN t.bus bs
       JOIN bs.office ao
       WHERE ao.agency.agencyId = :agencyId
       """)
    List<Customer> findCustomerByAgencyId(Integer agencyId);
    @Query("SELECT c FROM Customer c JOIN c.address a " +
            "WHERE c.name = :name AND a.address = :address")
    List<Customer> findByNameAndAddress( @Param("name") String name,
                                         @Param("address") String address);


}
