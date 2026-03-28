package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.BusResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgencyOfficeService {
    List<BusResponseDto> getBusesByOfficeId(Integer officeId);
}
