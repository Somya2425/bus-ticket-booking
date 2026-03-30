package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.time.LocalDateTime;


@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    List<Bus> findByOffice_OfficeId(Integer officeId);
    @Query("""
        SELECT DISTINCT b FROM Bus b
        JOIN b.office ao
        JOIN ao.agency a
        JOIN Trip t ON t.bus.busId = b.busId
        WHERE a.agencyId = :agencyId
        AND t.tripDate = :tripDate
    """)
    List<Bus> findBusesByAgencyIdAndDate(@Param("agencyId") Integer agencyId, @Param("tripDate") LocalDateTime tripDate);
}
