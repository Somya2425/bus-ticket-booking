package com.cg.busbooking.dto.response;

import lombok.Data;

@Data
public class BookingResponseDto {
    private Integer bookingId;
    private Integer seatNumber;
    private String status;
}
