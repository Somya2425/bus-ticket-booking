package com.aditi.transitflow.service.impl;

import com.aditi.transitflow.constants.AgencyOfficeConstants;
import com.aditi.transitflow.dto.response.OfficeDriverResponseDto;
import com.aditi.transitflow.dto.response.OfficeBusResponseDto;
import com.aditi.transitflow.entity.AgencyOffice;
import com.aditi.transitflow.entity.Bus;
import com.aditi.transitflow.entity.Driver;
import com.aditi.transitflow.exception.ResourceNotFoundException;
import com.aditi.transitflow.repository.AgencyOfficeRepository;
import com.aditi.transitflow.repository.BusRepository;
import com.aditi.transitflow.repository.DriverRepository;
import com.aditi.transitflow.service.AgencyOfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link AgencyOfficeService}.
 * Handles business logic for fetching buses and
 * drivers associated with an agency office.
 */
@Service
@RequiredArgsConstructor
public class AgencyOfficeServiceImpl implements AgencyOfficeService {

    private final BusRepository busRepository;
    private final AgencyOfficeRepository agencyOfficeRepository;
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;

    /**
     * Retrieves all buses associated with a given office ID.
     * Validates the existence of the office before fetching buses.
     *
     * @param officeId the ID of the agency office
     * @return list of buses mapped to OfficeBusResponseDto
     * @throws ResourceNotFoundException if the office does not exist
     */
    @Override
    public List<OfficeBusResponseDto> getBusesByOfficeId(Integer officeId) {

        AgencyOffice office = agencyOfficeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AgencyOfficeConstants.OFFICE_NOT_FOUND + officeId
                ));
        List<Bus> buses = busRepository.findByOffice_OfficeId(officeId);
        return buses.stream()
                .map(bus -> modelMapper.map(bus, OfficeBusResponseDto.class))
                .toList();
    }

    /**
     * Retrieves all drivers associated with a given office ID.
     * Validates the existence of the office before fetching drivers.
     *
     * @param officeId the ID of the agency office
     * @return list of drivers mapped to OfficeDriverResponseDto
     * @throws ResourceNotFoundException if the office does not exist
     */
    @Override
    public List<OfficeDriverResponseDto> getDriversByOfficeId(Integer officeId) {
        AgencyOffice office = agencyOfficeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AgencyOfficeConstants.OFFICE_NOT_FOUND + officeId
                ));
        List<Driver> drivers = driverRepository.findByOffice_OfficeId(officeId);
        return drivers.stream()
                .map(driver -> modelMapper.map(driver, OfficeDriverResponseDto.class))
                .toList();
    }
}