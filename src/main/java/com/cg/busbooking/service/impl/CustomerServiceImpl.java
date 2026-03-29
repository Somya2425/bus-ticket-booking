package com.cg.busbooking.service.impl;

import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.CustomerRepository;
import com.cg.busbooking.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Customer> getCustomerByNameAndAddress(String name, String address) {
        return customerRepository.findByNameAndAddress(name, address);
    }

    @Override
    public List<Booking> getCustomerBookings(Integer customerId) {
        return bookingRepository.findBookingsByCustomerId(customerId);
    }

    @Override
    public List<Customer> getCustomersByAgency(Integer agencyId) {
        return customerRepository.findCustomersByAgency(agencyId);
    }
}
