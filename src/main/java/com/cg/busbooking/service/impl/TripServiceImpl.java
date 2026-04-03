package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.entity.Trip;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.TripService;
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
                        new ResourceNotFoundException(String.format(TripConstants.TRIP_NOT_FOUND + " %d", tripId))
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