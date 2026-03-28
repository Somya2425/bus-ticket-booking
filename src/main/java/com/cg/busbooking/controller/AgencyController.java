package com.cg.busbooking.controller;

import com.cg.busbooking.dto.response.AgencyOfficeResponseDto;
import com.cg.busbooking.dto.response.CustomerResponseDto;
import com.cg.busbooking.service.AgencyService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("{agencyId}")
    public ResponseEntity<List<CustomerResponseDto>> getCustomersOfAgency(@PathVariable Integer agencyId) {
        List<CustomerResponseDto> customers = agencyService.getCustomersByAgencyId(agencyId)
                .stream()
                .map(c -> modelMapper.map(c, CustomerResponseDto.class))
                .toList();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("{agencyId}/offices")
    public ResponseEntity<List<AgencyOfficeResponseDto>> getOfficesOfAgency(@PathVariable Integer agencyId) {
        List<AgencyOfficeResponseDto> offices=agencyService.getOfficesByAgencyId(agencyId)
                .stream()
                .map(o->modelMapper.map(o,AgencyOfficeResponseDto.class))
                .toList();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }
}
