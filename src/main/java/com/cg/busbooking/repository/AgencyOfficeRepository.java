package com.cg.busbooking.repository;

import com.cg.busbooking.entity.AgencyOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyOfficeRepository extends JpaRepository<AgencyOffice,Integer> {
}
