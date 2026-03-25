package com.cg.busbooking.entity;

import com.cg.busbooking.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
