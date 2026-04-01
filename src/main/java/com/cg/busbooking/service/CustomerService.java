package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDto> getCustomerByNameAndAddress(String name, String address);

    List<BookingResponseDto> getCustomerBookings(Integer customerId);

    List<CustomerResponseDto> getCustomersByAgency(Integer agencyId);


}
