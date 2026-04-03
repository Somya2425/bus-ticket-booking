package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.OfficeDriverResponseDto;
import com.cg.busbooking.dto.response.OfficeBusResponseDto;

import java.util.List;

/**
 * Service interface for handling Agency Office related operations.
 * Defines business logic for fetching buses and drivers associated with an office.
 */
public interface AgencyOfficeService {

    /**
     * Retrieves all buses associated with a given office ID.
     *
     * @param officeId the ID of the agency office
     * @return list of buses for the specified office
     */
    List<OfficeBusResponseDto> getBusesByOfficeId(Integer officeId);

    /**
     * Retrieves all drivers associated with a given office ID.
     *
     * @param officeId the ID of the agency office
     * @return list of drivers for the specified office
     */
    List<OfficeDriverResponseDto> getDriversByOfficeId(Integer officeId);
}
