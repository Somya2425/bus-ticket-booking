package com.aditi.transitflow.service;
import com.aditi.transitflow.entity.Route;
import com.aditi.transitflow.exception.InvalidRouteException;
import com.aditi.transitflow.exception.ResourceNotFoundException;
import com.aditi.transitflow.exception.RouteNotFoundException;
import com.aditi.transitflow.repository.BookingRepository;
import com.aditi.transitflow.repository.RouteRepository;
import com.aditi.transitflow.service.impl.RouteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for RouteServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    /**
     * Mocked RouteRepository for testing.
     */
    @Mock
    private RouteRepository routeRepository;
    /**
     * Mocked BookingRepository for testing.
     */
    @Mock
    private BookingRepository bookingRepository;
    /**
     * Instance of RouteServiceImpl with injected mocks.
     */
    @InjectMocks
    private RouteServiceImpl routeService;

    /**
     * Verifies that routes are returned when
     * valid source and destination are provided.
     */
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

    /**
     * Verifies that RouteNotFoundException is thrown
     * when no routes exist.
     */
    @Test
    void testGetRouteBetweenCities_noRoutesFound() {
        String source = "CityA";
        String destination = "CityB";
        when(routeRepository.findByFromCityIgnoreCaseAndToCityIgnoreCase(source, destination)).thenReturn(Collections.emptyList());
        assertThrows(RouteNotFoundException.class, () -> routeService.getRouteBetweenCities(source, destination));
        verify(routeRepository, times(1)).findByFromCityIgnoreCaseAndToCityIgnoreCase(source, destination);
    }
    /**
     * Verifies that InvalidRouteException is thrown
     * when source and destination are the same.
     */

    @Test
    void testGetRouteBetweenCities_sameSourceAndDestination() {
        String city = "Mumbai";
        assertThrows(InvalidRouteException.class, () -> routeService.getRouteBetweenCities(city, city));
        verify(routeRepository, never()).findByFromCityIgnoreCaseAndToCityIgnoreCase(any(), any());
    }
    /**
     * Verifies that most popular routes
     * are returned successfully.
     */

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
    /**
     * Verifies that ResourceNotFoundException is thrown
     * when no popular routes exist.
     */
    @Test
    void testGetMostPopularRoute_noRoutesFound() {
        when(bookingRepository.findMostPopularRoutes()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> routeService.getMostPopularRoute());
        verify(bookingRepository).findMostPopularRoutes();
    }
}