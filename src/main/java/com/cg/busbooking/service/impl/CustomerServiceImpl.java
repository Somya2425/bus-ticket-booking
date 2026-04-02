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

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

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

    @Override
    public List<CustomerResponseDto> getCustomersByAgency(Integer agencyId) {
        List<Customer> customers = customerRepository.findCustomersByAgency(agencyId);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException(CustomerConstants.AGENCY_CUSTOMERS_NOT_FOUND);
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
