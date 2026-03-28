package com.cg.busbooking.service.impl;

import com.cg.busbooking.dto.response.RouteResponseDto;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.exception.RouteNotFoundException;
import com.cg.busbooking.repository.RouteRepository;
import com.cg.busbooking.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    public RouteServiceImpl(RouteRepository routeRepository,ModelMapper modelMapper){
        this.routeRepository=routeRepository;
        this.modelMapper = modelMapper;
    }
    public List<Route> getRouteBetweenCities(String source, String destination){
        List<Route> routes = routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source,destination);
        if(routes.isEmpty()){
            throw new RouteNotFoundException("No routes found between "+source+" and "+destination);
        }
        return routes;
    }
}
