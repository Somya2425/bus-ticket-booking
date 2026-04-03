package com.cg.busbooking.service.impl;
import com.cg.busbooking.constants.RouteConstants;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.exception.InvalidRouteException;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.exception.RouteNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.RouteRepository;
import com.cg.busbooking.service.RouteService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;

    public RouteServiceImpl(RouteRepository routeRepository,BookingRepository bookingRepository){
        this.routeRepository=routeRepository;
        this.bookingRepository = bookingRepository;
    }

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

    public List<Route> getMostPopularRoute() {
        List<Route> routes = bookingRepository.findMostPopularRoutes();
        if (routes.isEmpty()) {
            throw new ResourceNotFoundException(RouteConstants.BOOKINGS_NOT_FOUND);
        }
        return routes;
    }



}