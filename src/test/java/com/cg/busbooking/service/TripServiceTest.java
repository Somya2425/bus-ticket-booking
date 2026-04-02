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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TripServiceImpl tripServiceImpl;

    @Test
    void getAvailableSeatsForTrip_ShouldReturnSeats_WhenTripExists() {
        //
        // Arrange
        Integer tripId = 1;
        Trip mockTrip = new Trip();
        mockTrip.setAvailableSeats(30);

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(mockTrip));

        // Act
        AvailableSeatsResponseDto result = tripServiceImpl.getAvailableSeatsForTrip(tripId);

        // Assert
        assertNotNull(result);
        assertEquals(tripId, result.getTripId());
        assertEquals(30, result.getAvailableSeats());
        verify(tripRepository, times(1)).findById(tripId);
    }

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

        assertEquals(TripConstants.TRIP_NOT_FOUND, ex.getMessage());
        verify(tripRepository, times(1)).findById(tripId);
    }
    @Test
    void getTripBySourceAndDestination_ShouldReturnTrips_WhenTripsExist() {
        // Arrange
        String source = "Delhi";
        String destination = "Mumbai";

        Trip mockTrip = new Trip();
        TripResponseDto mockDto = new TripResponseDto();

        when(tripRepository.findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination))
                .thenReturn(List.of(mockTrip));
        when(modelMapper.map(mockTrip, TripResponseDto.class)).thenReturn(mockDto);

        // Act
        List<TripResponseDto> result = tripServiceImpl.getTripBySourceAndDestination(source, destination);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(mockDto, result.get(0));
        verify(tripRepository, times(1))
                .findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination);
    }

    @Test
    void getTripBySourceAndDestination_ShouldReturnEmptyList_WhenNoTripsFound() {
        // Arrange
        String source = "Agra";
        String destination = "Pune";

        when(tripRepository.findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination))
                .thenReturn(List.of());

        // Act
        List<TripResponseDto> result = tripServiceImpl.getTripBySourceAndDestination(source, destination);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(tripRepository, times(1))
                .findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination);
        verifyNoInteractions(modelMapper);
    }


}
