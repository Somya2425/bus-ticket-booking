package com.cg.busbooking.controller;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.ApiResponseDto;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.ErrorResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {
  private final TripService tripService;

  @GetMapping("/seats")
  public ResponseEntity<ApiResponseDto> getAvailableSeats(@RequestParam Integer tripId){
    AvailableSeatsResponseDto availableSeatsResponseDto = tripService.getAvailableSeatsForTrip(tripId);
      return ResponseEntity.status(HttpStatus.OK)
              .body(new ApiResponseDto(TripConstants.STATUS_200,TripConstants.MESSAGE_200,availableSeatsResponseDto));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponseDto> searchTrips(@RequestParam String source, @RequestParam String destination){
    List<TripResponseDto> tripResponseDto = tripService.getTripBySourceAndDestination(source,destination);
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseDto(TripConstants.STATUS_200,TripConstants.MESSAGE_200,tripResponseDto));
  }



}
