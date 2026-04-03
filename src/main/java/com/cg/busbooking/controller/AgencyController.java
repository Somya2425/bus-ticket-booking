package com.cg.busbooking.controller;

import com.cg.busbooking.constants.AgencyConstants;
import com.cg.busbooking.dto.response.AgencyOfficeResponseDto;
import com.cg.busbooking.dto.response.AgencyRevenueDto;
import com.cg.busbooking.dto.response.BusResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.service.AgencyService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for managing Agency-related operations.
 * This controller exposes APIs to retrieve information about agency.
 * @author Mahi
 */
@RestController
@RequestMapping("api/agency")
@Validated
public class AgencyController {
    /**
     * Service layer dependency for agency-related operations.
     */
    private final AgencyService agencyService;
    /**
     * Mapper used to convert Entity objects to DTOs.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructor-based dependency injection.
     *
     * @param agencyService Service layer for agency operations
     * @param modelMapper   Mapper to convert Entity to DTO
     */
    public AgencyController(AgencyService agencyService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.agencyService = agencyService;
    }

    private ResponseEntity<?> checkAccess(Integer agencyId, String role, Integer userAgencyId) {

        if (role.equals("CUSTOMER")) {
            return ResponseEntity.status(403).body("Customers are not allowed");
        }

        if (role.equals("AGENCY")) {
            if (userAgencyId == null || !userAgencyId.equals(agencyId)) {
                return ResponseEntity.status(403)
                        .body("Agency can access only their own data");
            }
        }
        return null;
    }

    /**
     * Fetch all customers associated with a given agency.
     * @param agencyId ID of the agency (must be positive and non-null)
     * @return List of CustomerResponseDto containing customer details
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     */
    @GetMapping("{agencyId}/customers")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersOfAgency(@PathVariable
              @NotNull(message = AgencyConstants.AGENCY_ID_NULL)
              @Min(value = 0,message = AgencyConstants.AGENCY_ID_POSITIVE)
              @Max(value = Integer.MAX_VALUE,message = AgencyConstants.AGENCY_ID_MAX)
              Integer agencyId,
              @RequestHeader("role") String role,
              @RequestHeader(value = "agencyId", required = false) Integer userAgencyId) {
        ResponseEntity access = checkAccess(agencyId, role, userAgencyId);
        if (access != null) return access;
        final List<CustomerResponseDto> customers = agencyService.getCustomersByAgencyId(agencyId)
                .stream()
                .map(c -> modelMapper.map(c, CustomerResponseDto.class))
                .toList();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /**
     * Fetch all offices under a given agency.
     * @param agencyId ID of the agency (must be positive and non-null)
     * @return List of AgencyOfficeResponseDto containing office details
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     */
    @GetMapping("{agencyId}/offices")
    public ResponseEntity<List<AgencyOfficeResponseDto>> getOfficesOfAgency(@PathVariable
            @NotNull(message = AgencyConstants.AGENCY_ID_NULL)
            @Min(value = 0,message = AgencyConstants.AGENCY_ID_POSITIVE)
            @Max(value = Integer.MAX_VALUE,message = AgencyConstants.AGENCY_ID_MAX+ Integer.MAX_VALUE)
            Integer agencyId,
            @RequestHeader("role") String role,
            @RequestHeader(value = "agencyId", required = false) Integer userAgencyId) {
        ResponseEntity access = checkAccess(agencyId, role, userAgencyId);
        if (access != null) return access;
        final List<AgencyOfficeResponseDto> offices=agencyService.getOfficesByAgencyId(agencyId)
                .stream()
                .map(o->modelMapper.map(o,AgencyOfficeResponseDto.class))
                .toList();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    /**
     * Fetch buses for a given agency on a specific date.
     * @param agencyId ID of the agency (must be positive and non-null)
     * @param tripDate Date and time of the trip (format: yyyy-MM-dd HH:mm:ss)
     * @return List of BusResponseDto containing bus details
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     */
    @GetMapping("{agencyId}/buses")
    public ResponseEntity<List<BusResponseDto>> getBusesOfAgencyByDate(@PathVariable
           @NotNull(message = AgencyConstants.AGENCY_ID_NULL)
           @Min(value = 0,message = AgencyConstants.AGENCY_ID_POSITIVE)
           @Max(value = Integer.MAX_VALUE,message = AgencyConstants.AGENCY_ID_MAX+ Integer.MAX_VALUE)
           Integer agencyId,
           @RequestParam
           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
           LocalDateTime tripDate,
           @RequestHeader("role") String role,
           @RequestHeader(value = "agencyId", required = false) Integer userAgencyId) {

        ResponseEntity access = checkAccess(agencyId, role, userAgencyId);
        if (access != null) return access;

        final List<BusResponseDto> buses=agencyService.getBusByAgencyIdAndDate(agencyId,tripDate)
                .stream()
                .map(bus-> modelMapper.map(bus,BusResponseDto.class))
                .toList();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    /**
     * Fetch total revenue generated by an agency.
     * @param agencyId ID of the agency (must be positive and non-null)
     * @return AgencyRevenueDto containing revenue details
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     */
    @GetMapping("{agencyId}/revenue")
    public ResponseEntity<AgencyRevenueDto> getRevenueOfAgency(@PathVariable
           @NotNull(message = AgencyConstants.AGENCY_ID_NULL)
           @Min(value = 0,message = AgencyConstants.AGENCY_ID_POSITIVE)
           @Max(value = Integer.MAX_VALUE,message = AgencyConstants.AGENCY_ID_MAX+ Integer.MAX_VALUE)
           Integer agencyId,
           @RequestHeader("role") String role) {

        if (!role.equals("ADMIN")) {
            return new ResponseEntity("Only ADMIN can access revenue", HttpStatusCode.valueOf(403));
        }
        return new ResponseEntity<>(agencyService.getAgencyRevenueByAgencyId(agencyId), HttpStatus.OK);
    }

}
