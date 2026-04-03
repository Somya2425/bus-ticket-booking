package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.OfficeDriverResponseDto;
import com.cg.busbooking.dto.response.OfficeBusResponseDto;
import com.cg.busbooking.service.AgencyOfficeService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for handling Agency Office related operations.
 * Provides endpoints to fetch buses and drivers associated with a specific office.
 */
@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor
@Validated
public class AgencyOfficeController {

    private final AgencyOfficeService agencyOfficeService;


    /**
     * Fetches all buses associated with a given office ID.
     *
     * @param officeId the ID of the agency office (must be greater than 0)
     * @return list of buses for the specified office
     */

    @GetMapping("/buses")
    public ResponseEntity<List<OfficeBusResponseDto>> getBusesByOfficeId(
            @RequestParam
            @Min(value = 1, message = "Office ID must be greater than 0")
            Integer officeId,
            @RequestHeader("role") String role,
            @RequestHeader(value = "officeId", required = false) Integer userOfficeId) {

        if (role.equals("CUSTOMER")) {
            return new ResponseEntity("Customers cannot access buses", HttpStatusCode.valueOf(403));
        }

        if (role.equals("AGENCY")) {
            if (userOfficeId == null || !userOfficeId.equals(officeId)) {
                return new ResponseEntity("Agency can only access their own office buses",HttpStatusCode.valueOf(403));
            }
        }
        List<OfficeBusResponseDto> buses =
                agencyOfficeService.getBusesByOfficeId(officeId);

        return ResponseEntity.ok(buses);
    }

    /**
     * Fetches all drivers associated with a given office ID.
     *
     * @param officeId the ID of the agency office (must be greater than 0)
     * @return list of drivers for the specified office
     */
    @GetMapping("/drivers")
    public ResponseEntity<List<OfficeDriverResponseDto>> getDriversByOfficeId(
            @RequestParam
            @Min(value = 1, message = "Office ID must be greater than 0")
            Integer officeId,
            @RequestHeader("role") String role,
            @RequestHeader(value = "officeId", required = false)Integer userOfficeId) {

        if (role.equals("CUSTOMER")) {
            return new ResponseEntity("Customers cannot access drivers",HttpStatusCode.valueOf(403));
        }

        // 🔥 AGENCY → only their own office
        if (role.equals("AGENCY")) {
            if (userOfficeId == null || !userOfficeId.equals(officeId)) {
                return new ResponseEntity("Agency can only access their own office drivers",HttpStatusCode.valueOf(403));
            }
        }

        List<OfficeDriverResponseDto> drivers =
                agencyOfficeService.getDriversByOfficeId(officeId);

        return ResponseEntity.ok(drivers);
    }
}