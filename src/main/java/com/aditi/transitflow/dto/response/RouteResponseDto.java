package com.aditi.transitflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing route details in responses.
 * This DTO is used to transfer route-related information between the backend
 * and client, including origin, destination, number of breakpoints,
 * and total travel duration.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponseDto {

    /**
     * Unique identifier of the route.
     */
    private Integer routeId;

    /**
     * Starting city of the route.
     */
    private String fromCity;

    /**
     * Destination city of the route.
     */
    private String toCity;

    /**
     * Number of intermediate stops (breakpoints) in the route.
     */
    private Integer breakPoints;

    /**
     * Total duration of the route (in minutes or hours, based on system design).
     */
    private Integer duration;
}
