package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.OfficeDriverResponseDto;
import com.cg.busbooking.dto.response.OfficeBusResponseDto;

import java.util.List;

public interface AgencyOfficeService {
    List<OfficeBusResponseDto> getBusesByOfficeId(Integer officeId);

    List<OfficeDriverResponseDto> getDriversByOfficeId(Integer officeId);
}
