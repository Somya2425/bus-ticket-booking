package com.cg.busbooking.service;

import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomerByNameAndAddress(String name, String address);

    List<Booking> getCustomerBookings(Integer customerId);

    List<Customer> getCustomersByAgency(Integer agencyId);
}
