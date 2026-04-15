package com.aditi.transitflow.service.impl;

import com.aditi.transitflow.constants.TripConstants;
import com.aditi.transitflow.dto.response.AvailableSeatsResponseDto;
import com.aditi.transitflow.dto.response.TripResponseDto;
import com.aditi.transitflow.entity.Trip;
import com.aditi.transitflow.exception.ResourceNotFoundException;
import com.aditi.transitflow.repository.TripRepository;
import com.aditi.transitflow.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link TripService} that contains business logic
 * for handling Trip-related operations.
 *
 * Responsibilities:
 * - Fetch available seats for a given trip
 * - Search trips based on source and destination
 *
 * Interacts with:
 * - {@link TripRepository} for database operations
 * - {@link ModelMapper} for entity-to-DTO conversion
 *
 * Logging:
 * - Logs request flow, success responses, and warning scenarios
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    /**
     * Repository layer dependency for Trip entity.
     */
    private final TripRepository tripRepository;

    /**
     * Used for mapping Entity objects to DTOs.
     */
    private final ModelMapper modelMapper;

    /**
     * Retrieves available seats for a specific trip.
     *
     * @param tripId ID of the trip
     * @return {@link AvailableSeatsResponseDto} containing tripId and available seats
     *
     * @throws ResourceNotFoundException if trip is not found
     */
    @Override
    public AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId) {

        log.info("Fetching available seats for tripId: {}", tripId);

        // Fetch trip from database
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format(TripConstants.TRIP_NOT_FOUND, tripId))
                );

        // Extract available seats
        Integer availableSeats = trip.getAvailableSeats();

        log.info("Available seats for tripId {}: {}", tripId, availableSeats);

        // Return response DTO
        return new AvailableSeatsResponseDto(tripId, availableSeats);
    }

    /**
     * Searches for trips based on source and destination.
     *
     * @param source      Starting city
     * @param destination Destination city
     * @return List of {@link TripResponseDto}
     *
     * @throws ResourceNotFoundException if no trips are found
     */
    @Override
    public List<TripResponseDto> getTripBySourceAndDestination(String source, String destination) {

        log.info("Searching trips from: {} to: {}", source, destination);

        // Fetch trips using derived query
        List<Trip> trips =
                tripRepository.findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(
                        source, destination
                );

        // Handle empty result
        if (trips.isEmpty()) {
            log.warn("No trips found from: {} to: {}", source, destination);

            throw new ResourceNotFoundException(
                    String.format(TripConstants.TRIPS_NOT_FOUND_BETWEEN, source, destination)
            );
        }

        log.info("Found {} trip(s) from: {} to: {}", trips.size(), source, destination);

        // Convert Entity list to DTO list
        return trips.stream()
                .map(trip -> modelMapper.map(trip, TripResponseDto.class))
                .toList();
    }
}