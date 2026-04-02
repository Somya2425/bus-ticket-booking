package com.cg.busbooking.service;

import com.cg.busbooking.entity.Route;

import java.util.List;
/**
 * Service interface for route-related business operations.
 * Defines methods to fetch routes between cities and
 * retrieve the most popular routes.
 */
public interface RouteService {
    /**
     * Retrieves routes between source and destination cities.
     * @param source source city
     * @param destination destination city
     * @return list of routes
     */
    List<Route> getRouteBetweenCities(String source, String destination);
    /**
     * Retrieves the most popular routes based on bookings.
     */
    List<Route> getMostPopularRoute();
}
