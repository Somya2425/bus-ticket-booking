package com.cg.busbooking.entity;

import com.cg.busbooking.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a booking made by a user for a specific trip.
 */
@Getter
@Setter
@Entity
@Table(name = "bookings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Booking {

    /**
     * Unique identifier for the booking.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    /**
     * Seat number assigned for the booking.
     */
    private Integer seatNumber;

    /**
     * Status of the booking (e.g., BOOKED, CANCELLED).
     * Stored as a string in the database.
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * The trip associated with this booking.
     * Represents a many-to-one relationship with Trip.
     */
    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonIgnore
    private Trip trip;
}