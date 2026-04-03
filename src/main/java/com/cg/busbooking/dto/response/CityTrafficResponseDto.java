package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing city-wise traffic statistics.
 *
 * This DTO is used to provide aggregated booking data for a specific city,
 * typically for analytics or reporting purposes.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityTrafficResponseDto {

    /**
     * Name of the city.
     */
    private String city;

    /**
     * Total number of bookings made in the city.
     */
    private Long totalBookings;
}
