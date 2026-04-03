package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.service.CustomerService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{name}/{address}")
    public ResponseEntity<List<CustomerResponseDto>> getCustomerByNameAndAddress(
            @PathVariable
            @NotNull(message = "Name cannot be null or empty.")
            @NotBlank(message = "Name cannot be blank.")
            String name,
            @PathVariable
            @NotNull(message = "Address cannot be null or empty.")
            @NotBlank(message = "Address cannot be blank.")
            String address,
            @RequestHeader("role") String role) {

        if (!role.equals("ADMIN")) {
            return new ResponseEntity("Only admin can access this API", HttpStatusCode.valueOf(403));
        }

        return ResponseEntity.ok(
                customerService.getCustomerByNameAndAddress(name, address)
        );
    }

    @GetMapping("{customerId}/bookings")
    public ResponseEntity<List<BookingResponseDto>> getCustomerBookings(
            @PathVariable
            @NotNull(message = "Customer Id cannot be null")
            @Min(value = 0,message = "Customer Id must be positive")
            @Max(value = Integer.MAX_VALUE,message = "Customer Id must be less than "+ Integer.MAX_VALUE)
            Integer customerId,
            @RequestHeader("role") String role,
            @RequestHeader(value = "userId", required = false) String userId) {

        if (role.equals("CUSTOMER")) {
            if (!userId.equals(String.valueOf(customerId))) {
                return new ResponseEntity("Customers can only view their own bookings", HttpStatus.UNAUTHORIZED);
            }
        }
        return ResponseEntity.ok(
                customerService.getCustomerBookings(customerId)
        );
    }

}
