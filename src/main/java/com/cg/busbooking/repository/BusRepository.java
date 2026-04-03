package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Repository interface for performing database operations on Bus entity.
 * This interface provides methods to:
 * - Fetch buses based on agency ID and trip date
 * - Fetch buses based on office ID
 * It extends JpaRepository to support basic CRUD operations.
 */
@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {

    /**
     * Retrieves buses associated with a specific agency ID and trip date.
     * This query joins Trip, Bus, AgencyOffice, and Agency entities
     * to filter buses belonging to an agency on a particular date.
     * @param agencyId the ID of the agency
     * @param tripDate the date of the trip
     * @return list of Bus entities matching the criteria
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
     * Retrieves buses associated with a specific office ID.
     * This is a derived query method provided by Spring Data JPA.
     * @param officeId the ID of the office
     * @return list of Bus entities belonging to the given office
     */
    List<Bus> findByOffice_OfficeId(Integer officeId);
}
