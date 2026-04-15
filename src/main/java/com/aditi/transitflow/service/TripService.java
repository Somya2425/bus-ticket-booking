package com.aditi.transitflow.service;

import com.aditi.transitflow.dto.response.AvailableSeatsResponseDto;
import com.aditi.transitflow.dto.response.TripResponseDto;

import java.util.List;

/**
 * Service interface for handling Trip-related business logic.
 *
 * This interface defines the contract for:
 * - Fetching available seats for a trip
 * - Searching trips based on source and destination
 *
 * Implementation of this interface will contain the actual business logic
 * and interact with the repository layer.
 */
public interface TripService {

    /**
     * Fetches available seats for a given trip.
     *
     * @param tripId ID of the trip
     * @return {@link AvailableSeatsResponseDto} containing seat availability details
     *
     * @throws RuntimeException if the trip is not found
     *
     * Example:
     * Input: tripId = 1
     * Output: Available seats count and details
     */
    AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId);

    /**
     * Retrieves a list of trips based on source and destination.
     *
     * @param source      Starting location of the trip
     * @param destination Destination location of the trip
     * @return List of {@link TripResponseDto} matching the search criteria
     *
     * Example:
     * Input: source = "Delhi", destination = "Pune"
     * Output: List of matching trips
     */
    List<TripResponseDto> getTripBySourceAndDestination(String source, String destination);
}