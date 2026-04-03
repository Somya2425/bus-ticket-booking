package com.cg.busbooking.service;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.entity.Trip;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.impl.TripServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link TripServiceImpl}.
 *
 * Purpose:
 * - Validate business logic of TripService
 * - Ensure correct interaction with repository layer
 * - Verify exception handling scenarios
 *
 * Tools Used:
 * - JUnit 5 for testing
 * - Mockito for mocking dependencies
 */
@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    /**
     * Mocked dependency for database operations.
     */
    @Mock
    private TripRepository tripRepository;

    /**
     * Mocked dependency for entity-to-DTO mapping.
     */
    @Mock
    private ModelMapper modelMapper;

    /**
     * Class under test with injected mocks.
     */
    @InjectMocks
    private TripServiceImpl tripServiceImpl;

    /**
     * Test case: Should return available seats when trip exists.
     */
    @Test
    void getAvailableSeatsForTrip_ShouldReturnSeats_WhenTripExists() {

        // Arrange
        Integer tripId = 1;
        Trip mockTrip = new Trip();
        mockTrip.setAvailableSeats(30);

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(mockTrip));

        // Act
        AvailableSeatsResponseDto result =
                tripServiceImpl.getAvailableSeatsForTrip(tripId);

        // Assert
        assertNotNull(result);
        assertEquals(tripId, result.getTripId());
        assertEquals(30, result.getAvailableSeats());

        verify(tripRepository, times(1)).findById(tripId);
    }

    /**
     * Test case: Should throw exception when trip is not found.
     */
    @Test
    void getAvailableSeatsForTrip_ShouldThrowException_WhenTripNotFound() {

        // Arrange
        Integer tripId = 99;
        when(tripRepository.findById(tripId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> tripServiceImpl.getAvailableSeatsForTrip(tripId)
        );

        assertEquals(
                String.format(TripConstants.TRIP_NOT_FOUND, tripId),
                ex.getMessage()
        );
        verify(tripRepository, times(1)).findById(tripId);
    }

    /**
     * Test case: Should return list of trips when matching trips exist.
     */
    @Test
    void getTripBySourceAndDestination_ShouldReturnTrips_WhenTripsExist() {

        // Arrange
        String source = "Delhi";
        String destination = "Mumbai";

        Trip mockTrip = new Trip();
        TripResponseDto mockDto = new TripResponseDto();

        when(tripRepository
                .findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination))
                .thenReturn(List.of(mockTrip));

        when(modelMapper.map(mockTrip, TripResponseDto.class))
                .thenReturn(mockDto);

        // Act
        List<TripResponseDto> result =
                tripServiceImpl.getTripBySourceAndDestination(source, destination);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(mockDto, result.get(0));

        verify(tripRepository, times(1))
                .findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination);
    }

    /**
     * Test case: Should handle scenario when no trips are found.
     */
    @Test
    void getTripBySourceAndDestination_ShouldHandleNoTripsFound() {

        // Arrange
        String source = "Agra";
        String destination = "Pune";

        when(tripRepository
                .findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination))
                .thenReturn(List.of());

        // Act & Assert
        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> tripServiceImpl.getTripBySourceAndDestination(source, destination)
        );

        assertEquals(
                String.format(TripConstants.TRIPS_NOT_FOUND_BETWEEN, source, destination),
                ex.getMessage()
        );

        verify(tripRepository, times(1))
                .findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination);

        verifyNoInteractions(modelMapper);
    }
}