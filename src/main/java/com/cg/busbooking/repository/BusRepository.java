package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    @Query("""
      SELECT DISTINCT t.bus
      FROM Trip t
      JOIN t.bus b
      JOIN b.office ao
      JOIN ao.agency a
      WHERE a.agencyId = :agencyId
      AND t.tripDate = :tripDate
    """)
    List<Bus> findBusesByAgencyIdAndDate(@Param("agencyId") Integer agencyId, @Param("tripDate") LocalDateTime tripDate);
    List<Bus> findByOffice_OfficeId(Integer officeId);
}
