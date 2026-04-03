package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Driver entities.
 * Provides database access methods for driver-related operations.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    /**
     * Retrieves all drivers associated with a specific office ID.
     *
     * @param officeId the ID of the office
     * @return list of drivers linked to the given office
     */
    List<Driver> findByOffice_OfficeId(Integer officeId);
}
