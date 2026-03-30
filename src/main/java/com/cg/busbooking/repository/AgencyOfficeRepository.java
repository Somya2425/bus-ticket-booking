package com.cg.busbooking.repository;

import com.cg.busbooking.entity.AgencyOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyOfficeRepository extends JpaRepository<AgencyOffice,Integer> {
    @Query("""
        SELECT ao FROM AgencyOffice ao
        WHERE ao.agency.agencyId = :agencyId
    """)
    List<AgencyOffice> findByAgencyId(Integer agencyId);
}
