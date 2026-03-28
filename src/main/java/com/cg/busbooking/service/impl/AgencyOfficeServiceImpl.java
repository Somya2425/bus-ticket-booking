package com.cg.busbooking.service.impl;

import com.cg.busbooking.dto.response.BusResponseDto;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.repository.AgencyOfficeRepository;
import com.cg.busbooking.repository.BusRepository;
import com.cg.busbooking.service.AgencyOfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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

        // 1. Validate input
        if (officeId == null) {
            throw new IllegalArgumentException("Office ID cannot be null");
        }

        // 2. Check if office exists (important for clean API behavior)
        AgencyOffice office = agencyOfficeRepository.findById(officeId)
                .orElseThrow(() -> new RuntimeException("Agency Office not found with id: " + officeId));

        // 3. Fetch buses efficiently (no need to load office.getBuses())
        List<Bus> buses = busRepository.findByOffice_OfficeId(officeId);

        // 4. Map to DTO using ModelMapper
        return buses.stream()
                .map(bus -> modelMapper.map(bus, BusResponseDto.class))
                .toList();
    }
}