package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer/")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{name}/{address}")
    public ResponseEntity<List<CustomerResponseDto>> getCustomerByNameAndAddress(
            @PathVariable String name,
            @PathVariable String address) {

        return ResponseEntity.ok(
                customerService.getCustomerByNameAndAddress(name, address)
        );
    }

    @GetMapping("/customer/{customerId}/bookings")
    public ResponseEntity<List<BookingResponseDto>> getCustomerBookings(
            @PathVariable Integer customerId) {

        return ResponseEntity.ok(
                customerService.getCustomerBookings(customerId)
        );
    }

}
