package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing trip details in responses.
 *
 * This DTO is used to provide comprehensive information about a trip,
 * including route details, timing, seat availability, and fare.
 *
 * It is typically used in trip search, booking, and scheduling APIs.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponseDto {

    /**
     * Unique identifier of the trip.
     */
    private Integer tripId;

    /**
     * Identifier of the route associated with the trip.
     */
    private Integer routeId;

    /**
     * Identifier of the bus assigned to the trip.
     */
    private Integer busId;

    /**
     * Starting city of the trip.
     */
    private String fromCity;

    /**
     * Destination city of the trip.
     */
    private String toCity;

    /**
     * Departure date and time of the trip.
     */
    private LocalDateTime departureTime;

    /**
     * Arrival date and time of the trip.
     */
    private LocalDateTime arrivalTime;

    /**
     * Number of seats currently available for booking.
     */
    private Integer availableSeats;

    /**
     * Fare for the trip.
     */
    private Double fare;

    /**
     * Date of the trip.
     */
    private LocalDateTime tripDate;
}