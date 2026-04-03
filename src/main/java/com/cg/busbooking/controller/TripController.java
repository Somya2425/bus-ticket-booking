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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller responsible for handling Trip-related operations.
 * It uses TripService to process business logic and returns responses
 * wrapped in ApiResponseDto for standardized API responses.
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

  /**
   * Service layer dependency for trip-related operations.
   */
  private final TripService tripService;

  /**
   * Retrieves the number of available seats for a given trip.
   * @param tripId the ID of the trip (must be non-null and greater than 0)
   * @return ResponseEntity containing:
   *         - HTTP 200 (OK)
   *         - ApiResponseDto with status, message, and available seat details
   */
  @GetMapping("/seats")
  public ResponseEntity<ApiResponseDto> getAvailableSeats(
          @RequestParam
          @NotNull(message = "Trip ID is required")
          @Min(value = 1, message = "Trip ID must be greater than 0")
          Integer tripId) {

    AvailableSeatsResponseDto availableSeatsResponseDto =
            tripService.getAvailableSeatsForTrip(tripId);

    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseDto(
                    TripConstants.STATUS_200,
                    TripConstants.SEATS_FOUND,
                    availableSeatsResponseDto
            ));
  }

  /**
   * Searches for trips between a given source and destination.
   * @param source the starting location (must not be blank)
   * @param destination the destination location (must not be blank)
   * @return ResponseEntity containing:
   *         - HTTP 200 (OK)
   *         - ApiResponseDto with status, message, and list of trips
   */
  @GetMapping("/search")
  public ResponseEntity<ApiResponseDto> searchTrips(
          @RequestParam
          @NotBlank(message = "Source cannot be empty")
          String source,

          @RequestParam
          @NotBlank(message = "Destination cannot be empty")
          String destination) {

    List<TripResponseDto> tripResponseDto =
            tripService.getTripBySourceAndDestination(source, destination);

    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseDto(
                    TripConstants.STATUS_200,
                    TripConstants.TRIP_FOUND,
                    tripResponseDto
            ));
  }
}