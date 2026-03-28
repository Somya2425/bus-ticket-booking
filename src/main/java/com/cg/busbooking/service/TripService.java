package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;

public interface TripService {
    AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId);
}
