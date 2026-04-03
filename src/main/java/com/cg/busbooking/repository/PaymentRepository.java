package com.cg.busbooking.repository;

import com.cg.busbooking.dto.response.AgencyRevenueDto;
import com.cg.busbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("""
        SELECT new com.cg.busbooking.dto.response.AgencyRevenueDto(
                ao.agency.agencyId,ao.agency.name,
                SUM(p.amount)
            )
        FROM Payment p
        JOIN p.booking b
        JOIN b.trip t   
        JOIN t.bus bus
        JOIN bus.office ao
        WHERE ao.agency.agencyId=:agencyId
    """)
    AgencyRevenueDto findRevenueByAgencyId(@Param("agencyId") Integer agencyId);
}
