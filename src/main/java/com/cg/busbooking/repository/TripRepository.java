package com.cg.busbooking.repository;
import com.cg.busbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing database operations on Trip entity.
 * This interface provides methods to:
 * - Perform basic CRUD operations using JpaRepository
 * - Fetch trips based on source and destination cities
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    /**
     * Retrieves trips based on source and destination cities (case-insensitive).
     * This is a derived query method provided by Spring Data JPA.
     * @param source the source city name
     * @param destination the destination city name
     * @return list of Trip entities matching the given source and destination
     */
    List<Trip> findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(String source, String destination);

}
