package com.cg.busbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing a trip with schedule, fare,
 * seat availability, and associated route, bus, drivers, and locations.
 */
@Getter
@Setter
@Entity
@Table(name = "trips")
public class Trip {
    /** Unique identifier for the trip. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripId;

    /** Departure date and time of the trip. */
    private LocalDateTime departureTime;

    /** Arrival date and time of the trip. */
    private LocalDateTime arrivalTime;

    /** Number of available seats for the trip. */
    private Integer availableSeats;

    /** Fare for the trip. */
    private Double fare;

    /** Date of the trip. */
    private LocalDateTime tripDate;

    /** Route associated with this trip. */
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    /** Bus assigned for the trip. */
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    /** Boarding address for the trip. */
    @ManyToOne
    @JoinColumn(name = "boarding_address_id")
    private Address boardingAddress;

    /** Dropping address for the trip. */
    @ManyToOne
    @JoinColumn(name = "dropping_address_id")
    private Address droppingAddress;

    /** Primary driver assigned to the trip. */
    @ManyToOne
    @JoinColumn(name = "driver1_driver_id")
    private Driver driver1;

    /** Secondary driver assigned to the trip. */
    @ManyToOne
    @JoinColumn(name = "driver2_driver_id")
    private Driver driver2;
}