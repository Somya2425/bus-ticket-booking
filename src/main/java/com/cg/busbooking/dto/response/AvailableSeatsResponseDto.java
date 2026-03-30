package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSeatsResponseDto {
    Integer tripId;
    Integer availableSeats;
    long bookedSeatsCount;

    public AvailableSeatsResponseDto(Integer tripId, Integer availableSeats) {
        this.tripId = tripId;
        this.availableSeats = availableSeats;
    }
}
