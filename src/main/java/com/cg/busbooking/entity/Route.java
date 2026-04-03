package com.cg.busbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a route with source,
 * destination, stops, and travel duration details.
 */
@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route {

    /** Unique identifier for the route. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    /** Starting city of the route. */
    private String fromCity;

    /** Destination city of the route. */
    private String toCity;

    /** Number of break points or stops in the route. */
    private Integer breakPoints;

    /** Total duration of the route in hours. */
    private Integer duration;
}