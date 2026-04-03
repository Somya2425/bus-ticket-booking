package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.BookingResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.service.CustomerService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling customer-related API requests.
 * This controller provides endpoints for:
 * - Fetching customers by name and address
 * - Fetching bookings of a specific customer
 * It interacts with the service layer to process business logic.
 */
@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    /**
     * Service layer dependency for customer operations.
     */
    private final CustomerService customerService;

    /**
     * Constructor for CustomerController.
     * @param customerService service for handling customer-related operations
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves customers based on name and address.
     * @param name the name of the customer (must not be null or blank)
     * @param address the address of the customer (must not be null or blank)
     * @return ResponseEntity containing list of CustomerResponseDto
     */
    @GetMapping("{name}/{address}")
    public ResponseEntity<List<CustomerResponseDto>> getCustomerByNameAndAddress(
            @PathVariable
            @NotNull(message = "Name cannot be null or empty.")
            @NotBlank(message = "Name cannot be blank.")
            String name,
            @PathVariable
            @NotNull(message = "Address cannot be null or empty.")
            @NotBlank(message = "Address cannot be blank.")
            String address) {

        return ResponseEntity.ok(
                customerService.getCustomerByNameAndAddress(name, address)
        );
    }

    /**
     * Retrieves all bookings for a given customer ID.
     * @param customerId the ID of the customer (must be a positive number)
     * @return ResponseEntity containing list of BookingResponseDto
     */
    @GetMapping("{customerId}/bookings")
    public ResponseEntity<List<BookingResponseDto>> getCustomerBookings(
            @PathVariable
            @NotNull(message = "Customer Id cannot be null")
            @Min(value = 0,message = "Customer Id must be positive")
            @Max(value = Integer.MAX_VALUE,message = "Customer Id must be less than "+ Integer.MAX_VALUE)
            Integer customerId
    ) {

        return ResponseEntity.ok(
                customerService.getCustomerBookings(customerId)
        );
    }

}
