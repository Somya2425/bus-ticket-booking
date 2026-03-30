package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.AgencyOfficeResponseDto;
import com.cg.busbooking.dto.response.BusResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.service.AgencyService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/agency/")
public class AgencyController {
    private final AgencyService agencyService;
    private final ModelMapper modelMapper;

    public AgencyController(AgencyService agencyService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.agencyService = agencyService;
    }
    
    @GetMapping("{agencyId}/offices")
    public ResponseEntity<List<AgencyOfficeResponseDto>> getOfficesOfAgency(@PathVariable Integer agencyId) {
        List<AgencyOfficeResponseDto> offices=agencyService.getOfficesByAgencyId(agencyId)
                .stream()
                .map(o->modelMapper.map(o,AgencyOfficeResponseDto.class))
                .toList();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping("{agencyId}/buses")
    public ResponseEntity<List<BusResponseDto>> getBusesOfAgencyByDate(@PathVariable Integer agencyId, @RequestParam LocalDateTime tripDate) {
        List<BusResponseDto> buses=agencyService.getBusByAgencyIdAndDate(agencyId,tripDate)
                .stream()
                .map(bus-> modelMapper.map(bus,BusResponseDto.class))
                .toList();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }
}
