package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing Bus entities.
 * Provides database access methods for bus-related operations.
 */
@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {

    /**
     * Retrieves distinct buses associated with a given agency and trip date.
     * This query joins Trip, Bus, Office, and Agency entities to filter results.
     *
     * @param agencyId the ID of the agency
     * @param tripDate the date of the trip
     * @return list of buses operating under the specified agency on the given date
     */
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

    /**
     * Retrieves all buses associated with a specific office ID.
     *
     * @param officeId the ID of the office
     * @return list of buses linked to the given office
     */
    List<Bus> findByOffice_OfficeId(Integer officeId);
}
