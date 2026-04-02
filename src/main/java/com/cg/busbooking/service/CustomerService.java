package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;

import java.util.List;

/**
 * Service interface for customer-related operations.
 *
 * This interface defines methods for:
 * - Fetching customers based on name and address
 * - Fetching bookings of a customer
 */
public interface CustomerService {

    /**
     * Retrieves customers based on name and address.
     *
     * @param name the name of the customer
     * @param address the address of the customer
     * @return list of CustomerResponseDto containing customer details
     */
    List<CustomerResponseDto> getCustomerByNameAndAddress(String name, String address);

    /**
     * Retrieves all bookings for a given customer ID.
     *
     * @param customerId the ID of the customer
     * @return list of BookingResponseDto containing booking details
     */
    List<BookingResponseDto> getCustomerBookings(Integer customerId);

}
