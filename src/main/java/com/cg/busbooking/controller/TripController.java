package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.AvailableSeatsResponseDto;
import com.cg.busbooking.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {
  private final TripService tripService;

  @GetMapping("/seats")
  public ResponseEntity<AvailableSeatsResponseDto> getAvailableSeats(@RequestParam Integer tripId){
      return ResponseEntity.ok(tripService.getAvailableSeatsForTrip(tripId));
  }

}
