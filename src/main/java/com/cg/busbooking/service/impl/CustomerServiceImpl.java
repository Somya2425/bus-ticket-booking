package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.CustomerConstants;
import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.CustomerRepository;
import com.cg.busbooking.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for handling customer-related operations.
 * This class contains business logic for:
 * - Fetching bookings of a customer
 * - Fetching customers by name and address
 * It interacts with the repository layer and converts entities into DTOs.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /** Repository for accessing customer data */
    private final CustomerRepository customerRepository;

     /** Repository for accessing booking data */
    private final BookingRepository bookingRepository;

    /**
     * Constructor for CustomerServiceImpl.
     * @param customerRepository repository for customer data
     * @param bookingRepository repository for booking data
     */
    public CustomerServiceImpl(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Retrieves all bookings for a given customer ID.
     * @param customerId the ID of the customer
     * @return list of BookingResponseDto containing booking details
     * @throws ResourceNotFoundException if no bookings are found for the customer
     */
    @Override
    public List<BookingResponseDto> getCustomerBookings(Integer customerId) {
        List<Booking> bookings = bookingRepository.findBookingsByCustomerId(customerId);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException(CustomerConstants.BOOKINGS_NOT_FOUND);
        }
        return bookings.stream().map(b -> {
            BookingResponseDto dto = new BookingResponseDto();
            dto.setBookingId(b.getBookingId());
            dto.setSeatNumber(b.getSeatNumber());
            if (b.getStatus() != null) {
                dto.setStatus(b.getStatus().toString());
            } else {
                dto.setStatus("UNKNOWN");
            }
            return dto;
        }).toList();
    }

    /**
     * Retrieves customers based on name and address.
     *
     * @param name the name of the customer
     * @param address the address of the customer
     * @return list of CustomerResponseDto containing customer details
     * @throws ResourceNotFoundException if no customers are found
     */
    @Override
    public List<CustomerResponseDto> getCustomerByNameAndAddress(String name, String address) {
        List<Customer> customers = customerRepository.findByNameAndAddress(name, address);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException(CustomerConstants.CUSTOMER_NOT_FOUND);
        }
        return customers.stream().map(c -> {
            CustomerResponseDto dto = new CustomerResponseDto();
            dto.setCustomerId(c.getCustomerId());
            dto.setName(c.getName());
            dto.setEmail(c.getEmail());
            dto.setPhone(c.getPhone());
            dto.setCity(c.getAddress().getCity());
            return dto;
        }).toList();
    }
}
