package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.entity.Address;
import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.CustomerRepository;
import com.cg.busbooking.service.impl.CustomerServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;


    @Test
    void testGetCustomerByNameAndAddress_success() {

        Address address = new Address();
        address.setAddress("Sukhdev Sadan, Allahpur");
        address.setCity("Prayagraj");
        address.setState("Uttar Pradesh");

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Aditya Gupta");
        customer.setAddress(address);

        List<Customer> customerList = List.of(customer);

        when(customerRepository.findByNameAndAddress("Aditya Gupta", "Sukhdev Sadan, Allahpur"))
                .thenReturn(customerList);

        List<CustomerResponseDto> result =
                customerServiceImpl.getCustomerByNameAndAddress("Aditya Gupta", "Sukhdev Sadan, Allahpur");

        assertEquals(1, result.size());
        assertEquals("Aditya Gupta", result.get(0).getName());
        assertEquals("Prayagraj", result.get(0).getCity());
        verify(customerRepository, times(1)).findByNameAndAddress("Aditya Gupta", "Sukhdev Sadan, Allahpur");
    }


    @Test
    void testGetCustomerByNameAndAddress_notFound() {

        when(customerRepository.findByNameAndAddress("Aditya Gupta", "Sukhdev Sadan, Allahpur"))
                .thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.getCustomerByNameAndAddress("Aditya Gupta", "Sukhdev Sadan, Allahpur"));
    }


    @Test
    void testGetCustomerBookings_success() {

        // Step 1: Create dummy Booking
        Booking booking = new Booking();
        booking.setBookingId(101);

        List<Booking> bookingList = List.of(booking);

        // Step 2: Mock repository

        when(bookingRepository.findBookingsByCustomerId(1))
                .thenReturn(bookingList);

        // Step 3: Call service
        List<BookingResponseDto> result = customerServiceImpl.getCustomerBookings(1);

        // Step 4: Assertions
        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getBookingId());
        verify(bookingRepository, times(1)).findBookingsByCustomerId(1);
    }



    // 🔸 Exception Case
    @Test
    void testGetCustomerBookings_notFound() {

        when(bookingRepository.findBookingsByCustomerId(1))
                .thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.getCustomerBookings(1);
        });
    }
}