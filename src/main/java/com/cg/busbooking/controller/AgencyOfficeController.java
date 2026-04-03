package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.OfficeDriverResponseDto;
import com.cg.busbooking.dto.response.OfficeBusResponseDto;
import com.cg.busbooking.service.AgencyOfficeService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor
@Validated
public class AgencyOfficeController {

    private final AgencyOfficeService agencyOfficeService;


    @GetMapping("/buses")
    public ResponseEntity<List<OfficeBusResponseDto>> getBusesByOfficeId(
            @RequestParam
            @Min(value = 1, message = "Office ID must be greater than 0")
            Integer officeId
    ) {
        List<OfficeBusResponseDto> buses =
                agencyOfficeService.getBusesByOfficeId(officeId);

        return ResponseEntity.ok(buses);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<OfficeDriverResponseDto>> getDriversByOfficeId(
            @RequestParam
            @Min(value = 1, message = "Office ID must be greater than 0")
            Integer officeId
    ) {
        List<OfficeDriverResponseDto> drivers =
                agencyOfficeService.getDriversByOfficeId(officeId);

        return ResponseEntity.ok(drivers);
    }
}