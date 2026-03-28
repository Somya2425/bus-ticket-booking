package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.BusResponseDto;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.service.AgencyOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor
public class AgencyOfficeController {

    private final AgencyOfficeService agencyOfficeService;

    // GET /api/offices/buses?officeId=1
    @GetMapping("/buses")
    public ResponseEntity<List<BusResponseDto>> getBusesByOfficeId(
            @RequestParam Integer officeId
    ) {
        List<BusResponseDto> buses =
                agencyOfficeService.getBusesByOfficeId(officeId);

        return ResponseEntity.ok(buses); // cleaner
    }
}