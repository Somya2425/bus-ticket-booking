package com.cg.busbooking.repository;

import com.cg.busbooking.entity.AgencyOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing AgencyOffice entities.
 * Provides database access methods for AgencyOffice operations.
 */
@Repository
public interface AgencyOfficeRepository extends JpaRepository<AgencyOffice,Integer> {

    /**
     * Retrieves all offices associated with a given agency ID.
     *
     * @param agencyId the ID of the agency
     * @return list of AgencyOffice entities linked to the given agency
     */
    @Query("""
        SELECT ao FROM AgencyOffice ao
        WHERE ao.agency.agencyId = :agencyId
    """)
    List<AgencyOffice> findByAgencyId(Integer agencyId);
}
