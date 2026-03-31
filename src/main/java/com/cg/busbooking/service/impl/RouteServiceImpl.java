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

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;
    private final BookingRepository bookingRepository;

    public RouteServiceImpl(RouteRepository routeRepository,TripRepository tripRepository,BookingRepository bookingRepository){
        this.routeRepository=routeRepository;
        this.tripRepository = tripRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Route> getRouteBetweenCities(String source, String destination){
        List<Route> routes = routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source,destination);
        if(routes.isEmpty()){
            throw new RouteNotFoundException("No routes found between "+source+" and "+destination);
        }
        return routes;
    }

    public List<Route> getMostPopularRoutes(Integer limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return tripRepository.findMostPopularRoutes(pageable);
    }


    public List<CityTrafficResponseDto> getTopCitiesByTraffic(Integer limit) {
        List<CityTrafficResponseDto> result = bookingRepository.getCityTraffic(PageRequest.of(0, limit));
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No traffic data found");
        }
        return result;
    }

}