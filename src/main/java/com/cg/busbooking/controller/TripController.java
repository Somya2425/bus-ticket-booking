package com.cg.busbooking.controller;

import com.cg.busbooking.constants.TripConstants;
import com.cg.busbooking.dto.response.ApiResponseDto;
import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.service.TripService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {
  private final TripService tripService;

  @GetMapping("/seats")
  public ResponseEntity<ApiResponseDto> getAvailableSeats(@RequestParam @NotNull(message = "Trip ID is required") @Min(value = 1, message = "Trip ID must be greater than 0") Integer tripId){
    AvailableSeatsResponseDto availableSeatsResponseDto = tripService.getAvailableSeatsForTrip(tripId);
      return ResponseEntity.status(HttpStatus.OK)
              .body(new ApiResponseDto(TripConstants.STATUS_200,TripConstants.SEATS_FOUND,availableSeatsResponseDto));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponseDto> searchTrips(@RequestParam @NotBlank(message = "Source cannot be empty") String source, @RequestParam @NotBlank(message = "Source cannot be empty") String destination){
    List<TripResponseDto> tripResponseDto = tripService.getTripBySourceAndDestination(source,destination);
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseDto(TripConstants.STATUS_200,TripConstants.TRIP_FOUND,tripResponseDto));
  }



}
