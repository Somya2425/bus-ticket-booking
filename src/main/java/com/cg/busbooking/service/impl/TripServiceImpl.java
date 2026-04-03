package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.entity.Trip;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.TripService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;

    @Override
    public AvailableSeatsResponseDto getAvailableSeatsForTrip(Integer tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(()-> new ResourceNotFoundException(TripConstants.TRIP_NOT_FOUND));
        Integer availableSeats = trip.getAvailableSeats();
        return new AvailableSeatsResponseDto(tripId,availableSeats);

    }

    @Override
    public List<TripResponseDto> getTripBySourceAndDestination(String source, String destination) {

        List<Trip> trips = tripRepository.findByRoute_FromCityIgnoreCaseAndRoute_ToCityIgnoreCase(source, destination);

        if (trips.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(TripConstants.TRIPS_NOT_FOUND_BETWEEN, source, destination)
            );
        }

        return trips.stream()
                .map(trip -> modelMapper.map(trip, TripResponseDto.class))
                .toList();
    }
}
