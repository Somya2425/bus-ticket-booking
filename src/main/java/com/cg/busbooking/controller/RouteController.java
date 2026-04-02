package com.cg.busbooking.controller;
import com.cg.busbooking.dto.response.RouteResponseDto;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.service.RouteService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for route-related operations.
 * Provides APIs to search routes and fetch popular routes.
 *
 * @author Priya
 */

@RestController
@RequestMapping("api/route")
@Validated
public class RouteController {
    /**
     * Service layer responsible for route-related business operations.
     */
    private final RouteService routeService;
    /**
     * ModelMapper instance used to convert Route entities into DTO objects.
     */
    private final ModelMapper modelMapper;
    /**
     * Creates RouteController with required dependencies.
     * @param routeService service for route operations
     * @param modelMapper mapper for entity to DTO conversion
     */

    public RouteController(final RouteService routeService, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.routeService = routeService;
    }
    /**
     * Retrieves routes between two cities.
     * @param fromCity source city
     * @param toCity destination city
     * @return list of route DTOs
     */

    @GetMapping("/search")
    public ResponseEntity<List<RouteResponseDto>> getRouteBetweenCities(@RequestParam("source")
            @NotNull(message = "Enter city name.")
            @NotBlank(message = "City name cannot be blank.")
            final String fromCity,
            @RequestParam("destination")
            @NotNull(message = "Enter city name.")
            @NotBlank(message = "City name cannot be blank.")
            final String toCity) {

        final List<RouteResponseDto> routes = routeService.getRouteBetweenCities(fromCity, toCity)
                .stream()
                .map(r -> modelMapper.map(r, RouteResponseDto.class))
                .toList();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    /**
     * Retrieves the most popular routes.
     * @return list of routes
     * @throws ResourceNotFoundException if no booking data exists
     */
    
    @GetMapping("/popular")
    public ResponseEntity<List<Route>> getMostPopularRoute() {
        return ResponseEntity.ok(routeService.getMostPopularRoute());
    }
}
