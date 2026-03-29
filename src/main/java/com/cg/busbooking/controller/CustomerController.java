package com.cg.busbooking.controller;

import com.cg.busbooking.entity.Booking;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // 1️⃣ Get customer by name & address
    @GetMapping("/customer/{name}/{address}")
    public ResponseEntity<List<Customer>> getCustomerByNameAndAddress(
            @PathVariable String name,
            @PathVariable String address) {

        return ResponseEntity.ok(
                customerService.getCustomerByNameAndAddress(name, address)
        );
    }

    @GetMapping("/customer/{customerId}/bookings")
    public ResponseEntity<List<Booking>> getCustomerBookings(
            @PathVariable Integer customerId) {

        return ResponseEntity.ok(
                customerService.getCustomerBookings(customerId)
        );
    }

    @GetMapping("/agency/{agencyId}/customers")
    public ResponseEntity<List<Customer>> getCustomersByAgency(
            @PathVariable Integer agencyId) {

        return ResponseEntity.ok(
                customerService.getCustomersByAgency(agencyId)
        );
    }
}
