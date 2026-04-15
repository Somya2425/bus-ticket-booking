package com.aditi.transitflow.repository;

import com.aditi.transitflow.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing database operations on Driver entity.
 * This interface provides methods to:
 * - Perform basic CRUD operations using JpaRepository
 * - Fetch drivers based on office ID
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    /**
     * Retrieves drivers associated with a specific office ID.
     * This is a derived query method provided by Spring Data JPA.
     * @param officeId the ID of the office
     * @return list of Driver entities belonging to the given office
     */
    List<Driver> findByOffice_OfficeId(Integer officeId);
}
