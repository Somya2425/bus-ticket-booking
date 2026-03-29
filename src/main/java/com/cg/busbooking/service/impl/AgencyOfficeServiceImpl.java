package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.AppConstants;
import com.cg.busbooking.dto.response.BusResponseDto;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.AgencyOfficeRepository;
import com.cg.busbooking.repository.BusRepository;
import com.cg.busbooking.service.AgencyOfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyOfficeServiceImpl implements AgencyOfficeService {

    private final BusRepository busRepository;
    private final AgencyOfficeRepository agencyOfficeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BusResponseDto> getBusesByOfficeId(Integer officeId) {

        //Check if office exists
        AgencyOffice office = agencyOfficeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AppConstants.OFFICE_NOT_FOUND + officeId
                ));

        //Fetch buses
        List<Bus> buses = busRepository.findByOffice_OfficeId(officeId);

        //Map to DTO
        return buses.stream()
                .map(bus -> modelMapper.map(bus, BusResponseDto.class))
                .toList();
    }
}