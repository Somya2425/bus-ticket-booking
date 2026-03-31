package com.cg.busbooking.service;
import com.cg.busbooking.dto.response.CityTrafficResponseDto;
import com.cg.busbooking.entity.Route;

import java.util.List;
public interface RouteService {
    List<Route> getRouteBetweenCities(String source, String destination);
    List<Route> getMostPopularRoutes(Integer limit);
    List<CityTrafficResponseDto> getTopCitiesByTraffic(Integer limit);
}
