package com.cg.busbooking.service.impl;

import com.cg.busbooking.constants.AgencyOfficeConstants;
import com.cg.busbooking.dto.response.OfficeDriverResponseDto;
import com.cg.busbooking.dto.response.OfficeBusResponseDto;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.entity.Driver;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.AgencyOfficeRepository;
import com.cg.busbooking.repository.BusRepository;
import com.cg.busbooking.repository.DriverRepository;
import com.cg.busbooking.service.AgencyOfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link AgencyOfficeService}.
 * Handles business logic for fetching buses and drivers associated with an agency office.
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