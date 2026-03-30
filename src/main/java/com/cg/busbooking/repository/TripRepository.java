package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Route;
import com.cg.busbooking.entity.Trip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    @Query("""
    SELECT t.route
    FROM Trip t
    GROUP BY t.route
    ORDER BY COUNT(t) DESC
    """)
    List<Route> findMostPopularRoutes(Pageable pageable);

}
