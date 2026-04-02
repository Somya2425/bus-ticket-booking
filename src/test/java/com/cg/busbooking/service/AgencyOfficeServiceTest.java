package com.cg.busbooking.service;

import com.cg.busbooking.repository.AgencyOfficeRepository;
import com.cg.busbooking.repository.BusRepository;
import com.cg.busbooking.repository.DriverRepository;
import com.cg.busbooking.service.impl.AgencyOfficeServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

public class AgencyOfficeServiceTest {
    @Mock
    private AgencyOfficeRepository agencyOfficeRepository;

    @Mock
    private BusRepository busRepository;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private AgencyOfficeServiceImpl agencyOfficeServiceImpl;
}
