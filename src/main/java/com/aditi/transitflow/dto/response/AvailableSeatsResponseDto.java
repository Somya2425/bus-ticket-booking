package com.aditi.transitflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing available seats of a trip.
 *
 * It is typically used in seat availability or booking-related APIs.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSeatsResponseDto {

    /**
     * Unique identifier of the trip.
     */
    private Integer tripId;

    /**
     * Number of seats currently available for the trip.
     */
    private Integer availableSeats;
}
