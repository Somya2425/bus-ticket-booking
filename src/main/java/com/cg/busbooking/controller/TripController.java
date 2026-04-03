package com.cg.busbooking.controller;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.ApiResponseDto;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.service.TripService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller responsible for handling Trip-related operations.
 *
 * Base URL: /trips
 *
 * Features:
 * - Fetch available seats for a given trip
 * - Search trips based on source and destination
 *
 * Uses:
 * - {@link TripService} for business logic
 * - {@link ApiResponseDto} for standardized API responses
 *
 * Logging:
 * - Logs incoming requests and successful responses for traceability
 */
@Validated
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

  /**
   * Service layer dependency to handle trip-related business logic.
   * Injected via constructor (Lombok @RequiredArgsConstructor).
   */
  private final TripService tripService;

  /**
   * Fetches available seats for a specific trip.
   *
   * Endpoint: GET /trips/seats
   *
   * @param tripId ID of the trip (must be non-null and greater than 0)
   * @return ResponseEntity containing:
   *         - HTTP status
   *         - status code (String)
   *         - message
   *         - available seats data
   *
   * Example:
   * GET /trips/seats?tripId=1
   */
  @GetMapping("/seats")
  public ResponseEntity<ApiResponseDto> getAvailableSeats(
          @RequestParam
          @NotNull(message = "Trip ID is required")
          @Min(value = 1, message = "Trip ID must be greater than 0")
          Integer tripId) {

    log.info("Request received to get available seats for tripId: {}", tripId);

    // Call service layer
    AvailableSeatsResponseDto availableSeatsResponseDto =
            tripService.getAvailableSeatsForTrip(tripId);

    log.info("Available seats fetched successfully for tripId: {}", tripId);

    // Return standardized response
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseDto(
                    TripConstants.STATUS_200,
                    TripConstants.SEATS_FOUND,
                    availableSeatsResponseDto
            ));
  }

  /**
   * Searches for trips based on source and destination.
   *
   * Endpoint: GET /trips/search
   *
   * @param source      Starting location (must not be blank)
   * @param destination Destination location (must not be blank)
   * @return ResponseEntity containing:
   *         - HTTP status
   *         - status code (String)
   *         - message
   *         - list of trips
   *
   * Example:
   * GET /trips/search?source=Delhi&destination=Pune
   */
  @GetMapping("/search")
  public ResponseEntity<ApiResponseDto> searchTrips(
          @RequestParam
          @NotBlank(message = "Source cannot be empty")
          String source,

          @RequestParam
          @NotBlank(message = "Destination cannot be empty")
          String destination) {

    log.info("Request received to search trips from: {} to: {}", source, destination);

    // Call service layer
    List<TripResponseDto> tripResponseDto =
            tripService.getTripBySourceAndDestination(source, destination);

    log.info("Trips fetched successfully from: {} to: {}, count: {}",
            source, destination, tripResponseDto.size());

    // Return standardized response
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseDto(
                    TripConstants.STATUS_200,
                    TripConstants.TRIP_FOUND,
                    tripResponseDto
            ));
  }
}