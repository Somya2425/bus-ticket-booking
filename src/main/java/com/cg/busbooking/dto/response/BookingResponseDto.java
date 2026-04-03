package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing booking details in responses.
 *
 * It is typically used in ticket booking and reservation APIs.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    /**
     * Unique identifier of the booking.
     */
    private Integer bookingId;

    /**
     * Id to identify trip
     */
    private Integer tripId;

    /**
     * Seat number allocated for the booking.
     */
    private Integer seatNumber;

    /**
     * Status of the booking (e.g., CONFIRMED, CANCELLED).
     */
    private String status;
}
