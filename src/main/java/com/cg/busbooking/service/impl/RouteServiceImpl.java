package com.cg.busbooking.service.impl;
import com.cg.busbooking.dto.response.CityTrafficResponseDto;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.exception.RouteNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.RouteRepository;
import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.RouteService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;

    public RouteServiceImpl(RouteRepository routeRepository,BookingRepository bookingRepository){
        this.routeRepository=routeRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Route> getRouteBetweenCities(String source, String destination){
        List<Route> routes = routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source,destination);
        if(routes.isEmpty()){
            throw new RouteNotFoundException("No routes found between "+source+" and "+destination);
        }
        return routes;
    }

    public List<Route> getMostPopularRoute() {
        List<Route> routes = bookingRepository.findMostPopularRoutes();
        if (routes.isEmpty()) {
            throw new RuntimeException("No bookings found");
        }
        return routes;
    }



}