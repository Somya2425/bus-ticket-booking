package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;

import java.util.List;

public interface TripService {
    AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId);


}
