package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.CityTrafficResponseDto;
import com.cg.busbooking.dto.response.RouteResponseDto;
import com.cg.busbooking.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/route")
public class RouteController {
    private final RouteService routeService;
    private final ModelMapper modelMapper;
    public RouteController(RouteService routeService, ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.routeService=routeService;
    }
    @GetMapping("/search")   //-----based on breakpoints
    public ResponseEntity<List<RouteResponseDto>> getRouteBetweenCities(@RequestParam("source")String fromCity, @RequestParam("destination")String toCity){
        List<RouteResponseDto> routes = routeService.getRouteBetweenCities(fromCity,toCity)
                .stream()
                .map(r-> modelMapper.map(r,RouteResponseDto.class))
                .toList();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<RouteResponseDto>> getPopularRoutes(@RequestParam(defaultValue = "1") Integer limit) {
        List<RouteResponseDto> routes = routeService.getMostPopularRoutes(limit)
                .stream()
                .map(r -> modelMapper.map(r, RouteResponseDto.class))
                .toList();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/traffic")
    public ResponseEntity<List<CityTrafficResponseDto>> getCityTraffic( @RequestParam(defaultValue = "1") Integer limit) {
        List<CityTrafficResponseDto> cities= routeService.getTopCitiesByTraffic(limit);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
