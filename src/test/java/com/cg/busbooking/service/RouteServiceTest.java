package com.cg.busbooking.service;

import com.cg.busbooking.entity.Route;
import com.cg.busbooking.exception.InvalidRouteException;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.exception.RouteNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.RouteRepository;
import com.cg.busbooking.service.impl.RouteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    @Mock
    private RouteRepository routeRepository;
    @Mock
    private BookingRepository bookingRepository;
    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    void testGetRouteBetweenCities_success() {
        String source = "Mumbai";
        String destination = "Pune";
        Route route = new Route();
        route.setFromCity("Mumbai");
        route.setToCity("Pune");
        List<Route> mockRoutes = List.of(route);

        when(routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source, destination)).thenReturn(mockRoutes);
        List<Route> result = routeService.getRouteBetweenCities(source, destination);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mumbai", result.getFirst().getFromCity());
        assertEquals("Pune", result.getFirst().getToCity());
        verify(routeRepository, times(1)).findByFromCityIgnoreCaseAndToCityIgnoreCase(source, destination);
    }


    @Test
    void testGetRouteBetweenCities_noRoutesFound() {
        String source = "CityA";
        String destination = "CityB";
        when(routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source, destination)).thenReturn(Collections.emptyList());
        assertThrows(RouteNotFoundException.class, () -> routeService.getRouteBetweenCities(source, destination));
        verify(routeRepository, times(1)).findByFromCityIgnoreCaseAndToCityIgnoreCase(source, destination);
    }

    @Test
    void testGetRouteBetweenCities_sameSourceAndDestination() {
        String city = "Mumbai";
        assertThrows(InvalidRouteException.class, () -> routeService.getRouteBetweenCities(city, city));
        verify(routeRepository, never()).findByFromCityIgnoreCaseAndToCityIgnoreCase(any(), any());
    }

    @Test
    void testGetMostPopularRoute_success() {
        Route route = new Route();
        route.setFromCity("Mumbai");
        route.setToCity("Pune");
        List<Route> mockRoutes = List.of(route);

        when(bookingRepository.findMostPopularRoutes()).thenReturn(mockRoutes);
        List<Route> result = routeService.getMostPopularRoute();
        assertEquals(1, result.size());
        assertEquals("Mumbai", result.getFirst().getFromCity());
        assertEquals("Pune", result.getFirst().getToCity());
        verify(bookingRepository).findMostPopularRoutes();
    }
    @Test
    void testGetMostPopularRoute_noRoutesFound() {
        when(bookingRepository.findMostPopularRoutes()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> routeService.getMostPopularRoute());
        verify(bookingRepository).findMostPopularRoutes();
    }

}