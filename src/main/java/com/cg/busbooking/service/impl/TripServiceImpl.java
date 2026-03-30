package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Trip;
import com.cg.busbooking.enums.Status;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.TripService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    private final BookingRepository bookingRepository;

    private final ModelMapper modelMapper;

    @Override
    public AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if (trip.isPresent()) {
            Integer availableSeats = trip.get().getAvailableSeats();
            return new AvailableSeatsResponseDto(tripId, availableSeats);

        }
        return new AvailableSeatsResponseDto(null, null);
    }
}
