package com.aditi.transitflow.service;

import com.aditi.transitflow.constants.AgencyOfficeConstants;
import com.aditi.transitflow.dto.response.OfficeBusResponseDto;
import com.aditi.transitflow.dto.response.OfficeDriverResponseDto;
import com.aditi.transitflow.entity.AgencyOffice;
import com.aditi.transitflow.entity.Bus;
import com.aditi.transitflow.entity.Driver;
import com.aditi.transitflow.exception.ResourceNotFoundException;
import com.aditi.transitflow.repository.AgencyOfficeRepository;
import com.aditi.transitflow.repository.BusRepository;
import com.aditi.transitflow.repository.DriverRepository;
import com.aditi.transitflow.service.impl.AgencyOfficeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link AgencyOfficeServiceImpl}.
 * Tests business logic related to fetching buses and drivers by office ID.
 */
@ExtendWith(MockitoExtension.class)
public class AgencyOfficeServiceTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private AgencyOfficeRepository agencyOfficeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private AgencyOfficeServiceImpl agencyOfficeServiceImpl;

    // -------------------- BUS TESTS --------------------

    /**
     * Verifies that bus DTOs are returned when the office exists.
     */
    @Test
    void shouldReturnBusDtos_whenOfficeExists() {
        Integer officeId = 1;

        AgencyOffice office = createOffice(officeId);

        Bus bus1 = new Bus();
        bus1.setBusId(101);
        bus1.setType("TYPE_A");

        Bus bus2 = new Bus();
        bus2.setBusId(102);
        bus2.setType("TYPE_B");

        OfficeBusResponseDto dto1 = new OfficeBusResponseDto(101, "TYPE_A");
        OfficeBusResponseDto dto2 = new OfficeBusResponseDto(102, "TYPE_B");

        when(agencyOfficeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(busRepository.findByOffice_OfficeId(officeId)).thenReturn(List.of(bus1, bus2));

        when(modelMapper.map(bus1, OfficeBusResponseDto.class)).thenReturn(dto1);
        when(modelMapper.map(bus2, OfficeBusResponseDto.class)).thenReturn(dto2);

        List<OfficeBusResponseDto> result = agencyOfficeServiceImpl.getBusesByOfficeId(officeId);

        assertEquals(2, result.size());
        assertEquals(dto1.getBusId(), result.get(0).getBusId());
        assertEquals(dto2.getBusId(), result.get(1).getBusId());

        verify(agencyOfficeRepository).findById(officeId);
        verify(busRepository).findByOffice_OfficeId(officeId);
    }

    /**
     * Verifies that ResourceNotFoundException is thrown
     * when office does not exist while fetching buses.
     */
    @Test
    void shouldThrowException_whenOfficeNotFound_forBuses() {
        Integer officeId = 11111;

        when(agencyOfficeRepository.findById(officeId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> agencyOfficeServiceImpl.getBusesByOfficeId(officeId)
        );

        assertEquals(AgencyOfficeConstants.OFFICE_NOT_FOUND + officeId, exception.getMessage());

        verify(busRepository, never()).findByOffice_OfficeId(anyInt());
        verify(modelMapper, never()).map(any(), eq(OfficeBusResponseDto.class));
    }

    // -------------------- DRIVER TESTS --------------------

    /**
     * Verifies that driver DTOs are returned when the office exists.
     */
    @Test
    void shouldReturnDriverDtos_whenOfficeExists() {
        Integer officeId = 1;

        AgencyOffice office = createOffice(officeId);

        Driver driver1 = new Driver();
        driver1.setDriverId(201);
        driver1.setName("Driver One");

        Driver driver2 = new Driver();
        driver2.setDriverId(202);
        driver2.setName("Driver Two");

        OfficeDriverResponseDto dto1 = new OfficeDriverResponseDto(201, "Driver One");
        OfficeDriverResponseDto dto2 = new OfficeDriverResponseDto(202, "Driver Two");

        when(agencyOfficeRepository.findById(officeId)).thenReturn(Optional.of(office));
        when(driverRepository.findByOffice_OfficeId(officeId)).thenReturn(List.of(driver1, driver2));

        when(modelMapper.map(driver1, OfficeDriverResponseDto.class)).thenReturn(dto1);
        when(modelMapper.map(driver2, OfficeDriverResponseDto.class)).thenReturn(dto2);

        List<OfficeDriverResponseDto> result = agencyOfficeServiceImpl.getDriversByOfficeId(officeId);

        assertEquals(2, result.size());
        assertEquals(dto1.getDriverId(), result.get(0).getDriverId());
        assertEquals(dto2.getDriverId(), result.get(1).getDriverId());

        verify(agencyOfficeRepository).findById(officeId);
        verify(driverRepository).findByOffice_OfficeId(officeId);
    }

    /**
     * Verifies that ResourceNotFoundException is thrown
     * when office does not exist while fetching drivers.
     */
    @Test
    void shouldThrowException_whenOfficeNotFound_forDrivers() {
        Integer officeId = 18729;

        when(agencyOfficeRepository.findById(officeId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> agencyOfficeServiceImpl.getDriversByOfficeId(officeId)
        );

        assertEquals(AgencyOfficeConstants.OFFICE_NOT_FOUND + officeId, exception.getMessage());

        verify(driverRepository, never()).findByOffice_OfficeId(anyInt());
        verify(modelMapper, never()).map(any(), eq(OfficeDriverResponseDto.class));
    }


    /**
     * Creates a mock AgencyOffice entity with the given ID.
     */
    private AgencyOffice createOffice(Integer officeId) {
        AgencyOffice office = new AgencyOffice();
        office.setOfficeId(officeId);
        return office;
    }
}