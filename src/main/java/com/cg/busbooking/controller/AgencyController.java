package com.cg.busbooking.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/agency/")
@Validated
public class AgencyController {
    private final AgencyService agencyService;
    private final ModelMapper modelMapper;

    public AgencyController(AgencyService agencyService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.agencyService = agencyService;
    }

    @GetMapping("{agencyId}/customers")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersOfAgency(@PathVariable
              @NotNull(message = "Agency Id cannot be null")
              @Min(value = 0,message = "Agency Id must be positive")
              @Max(value = Integer.MAX_VALUE,message = "Agency Id must be less than "+ Integer.MAX_VALUE)
              Integer agencyId) {
        List<CustomerResponseDto> customers = agencyService.getCustomersByAgencyId(agencyId)
                .stream()
                .map(c -> modelMapper.map(c, CustomerResponseDto.class))
                .toList();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("{agencyId}/offices")
    public ResponseEntity<List<AgencyOfficeResponseDto>> getOfficesOfAgency(@PathVariable
            @NotNull(message = "Agency Id cannot be null")
            @Min(value = 0,message = "Agency Id must be positive")
            @Max(value = Integer.MAX_VALUE,message = "Agency Id must be less than "+ Integer.MAX_VALUE)
            Integer agencyId) {
        List<AgencyOfficeResponseDto> offices=agencyService.getOfficesByAgencyId(agencyId)
                .stream()
                .map(o->modelMapper.map(o,AgencyOfficeResponseDto.class))
                .toList();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping("{agencyId}/buses")
    public ResponseEntity<List<BusResponseDto>> getBusesOfAgencyByDate(@PathVariable
           @NotNull(message = "Agency Id cannot be null")
           @Min(value = 0,message = "Agency Id must be positive")
           @Max(value = Integer.MAX_VALUE,message = "Agency Id must be less than "+ Integer.MAX_VALUE)
           Integer agencyId,
           @RequestParam
           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
           LocalDateTime tripDate) {
        List<BusResponseDto> buses=agencyService.getBusByAgencyIdAndDate(agencyId,tripDate)
                .stream()
                .map(bus-> modelMapper.map(bus,BusResponseDto.class))
                .toList();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("{agencyId}/revenue")
    public ResponseEntity<AgencyRevenueDto> getRevenueOfAgency(@PathVariable
           @NotNull(message = "Agency Id cannot be null")
           @Min(value = 0,message = "Agency Id must be positive")
           @Max(value = Integer.MAX_VALUE,message = "Agency Id must be less than "+ Integer.MAX_VALUE)
           Integer agencyId) {
        return new ResponseEntity<>(agencyService.getAgencyRevenueByAgencyId(agencyId), HttpStatus.OK);
    }

}
