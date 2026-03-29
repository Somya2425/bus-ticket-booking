package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.BusResponseDto;
import com.cg.busbooking.dto.response.DriverResponseDto;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgencyOfficeService {
    List<BusResponseDto> getBusesByOfficeId(Integer officeId);

    List<DriverResponseDto> getDriversByOfficeId(Integer officeId);
}
