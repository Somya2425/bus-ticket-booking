package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.RouteResponseDto;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.service.RouteService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/route")
@Validated
public class RouteController {
    private final RouteService routeService;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.routeService = routeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<RouteResponseDto>> getRouteBetweenCities(@RequestParam("source")
            @NotNull(message = "Enter city name.")
            @NotBlank(message = "City name cannot be blank.")
            String fromCity,
            @RequestParam("destination")
            @NotNull(message = "Enter city name.")
            @NotBlank(message = "City name cannot be blank.")
            String toCity) {

        List<RouteResponseDto> routes = routeService.getRouteBetweenCities(fromCity, toCity)
                .stream()
                .map(r -> modelMapper.map(r, RouteResponseDto.class))
                .toList();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Route>> getMostPopularRoute() {
        return ResponseEntity.ok(routeService.getMostPopularRoute());
    }

}
