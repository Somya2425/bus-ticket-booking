package com.cg.busbooking.dto.response;

import java.time.LocalDateTime;

public class TripResponse {
    private Integer tripId;
    private Integer routeId;
    private Integer busId;
    private String fromCity;
    private String toCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats;
    private Double fare;
    private LocalDateTime tripDate;
}
