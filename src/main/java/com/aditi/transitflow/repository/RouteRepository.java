package com.aditi.transitflow.repository;

import com.aditi.transitflow.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing Route entities.
 * Provides database operations for route-related queries.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
     /**
      * Finds routes between source and destination cities (case-insensitive).
      * @param fromCity source city
      * @param toCity destination city
      * @return list of matching routes
      */
     List<Route> findByFromCityIgnoreCaseAndToCityIgnoreCase(String fromCity, String toCity);


}
