package com.aditi.transitflow.service.impl;
import com.aditi.transitflow.constants.RouteConstants;
import com.aditi.transitflow.entity.Route;
import com.aditi.transitflow.exception.InvalidRouteException;
import com.aditi.transitflow.exception.ResourceNotFoundException;
import com.aditi.transitflow.exception.RouteNotFoundException;
import com.aditi.transitflow.repository.BookingRepository;
import com.aditi.transitflow.repository.RouteRepository;
import com.aditi.transitflow.service.RouteService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for route-related operations.
 * Handles route search and popular route retrieval.
 */
@Service
public class RouteServiceImpl implements RouteService {
    /**
     * Repository for route data access.
     */
    private final RouteRepository routeRepository;
    /**
     * Repository for booking-related data access.
     */
    private final BookingRepository bookingRepository;
    /**
     * Constructs RouteServiceImpl with required dependencies.
     * @param routeRepository repository for route operations
     * @param bookingRepository repository for booking operations
     */
    public RouteServiceImpl(RouteRepository routeRepository,BookingRepository bookingRepository){
        this.routeRepository=routeRepository;
        this.bookingRepository = bookingRepository;
    }
    /**
     * Retrieves routes between source and destination cities.
     * @param source source city
     * @param destination destination city
     * @return list of routes
     * @throws InvalidRouteException if source and destination are the same
     * @throws RouteNotFoundException if no routes are found
     */

    @Override
    public List<Route> getRouteBetweenCities(String source, String destination){
        if(source.equalsIgnoreCase(destination)){
            throw new InvalidRouteException(RouteConstants.INVALID_ROUTE);
        }

        List<Route> routes = routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source,destination);
        if(routes.isEmpty()){
            throw new RouteNotFoundException("No routes found between "+source+" and "+destination);
        }
        return routes;
    }
    /**
     * Retrieves the most popular routes based on bookings.
     * @return list of popular routes
     * @throws ResourceNotFoundException if no booking data is available
     */

    @Override
    public List<Route> getMostPopularRoute() {
        List<Route> routes = bookingRepository.findMostPopularRoutes();
        if (routes.isEmpty()) {
            throw new RouteNotFoundException(RouteConstants.BOOKINGS_NOT_FOUND);
        }
        return routes;
    }
}