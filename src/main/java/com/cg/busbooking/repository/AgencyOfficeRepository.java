package com.cg.busbooking.repository;

import com.cg.busbooking.entity.AgencyOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository interface for performing database operations on AgencyOffice entity.
 * This interface provides methods to:
 * - Perform basic CRUD operations using JpaRepository
 * - Fetch agency offices based on agency ID
 */
@Repository
public interface AgencyOfficeRepository extends JpaRepository<AgencyOffice,Integer> {

    /**
     * Retrieves all agency offices associated with a specific agency ID.
     *
     * @param agencyId the ID of the agency
     * @return list of AgencyOffice entities belonging to the given agency
     */
    @Query("""
        SELECT ao FROM AgencyOffice ao
        WHERE ao.agency.agencyId = :agencyId
    """)
    List<AgencyOffice> findByAgencyId(Integer agencyId);
}
