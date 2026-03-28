package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Trip;
import com.cg.busbooking.enums.Status;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    private final BookingRepository bookingRepository;

    @Override
    public AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException(TripConstants.TRIP_NOT_FOUND));
        long booked = bookingRepository.countByTripIdAndStatus(tripId, Status.Available);
        return new AvailableSeatsResponseDto(tripId, trip.getAvailableSeats(), booked);
    }
}
