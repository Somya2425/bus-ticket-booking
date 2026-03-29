package com.cg.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats;
    private Double fare;
    private LocalDateTime tripDate;

    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonIgnore
    private Route route;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonIgnore
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "boarding_address_id")
    @JsonIgnore
    private Address boardingAddress;

    @ManyToOne
    @JoinColumn(name = "dropping_address_id")
    @JsonIgnore
    private Address droppingAddress;

    @ManyToOne
    @JoinColumn(name = "driver1_driver_id")
    @JsonIgnore
    private Driver driver1;

    @ManyToOne
    @JoinColumn(name = "driver2_driver_id")
    @JsonIgnore
    private Driver driver2;
}
