package com.cg.busbooking.service;

import com.cg.busbooking.repository.*;
import com.cg.busbooking.service.impl.AgencyServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AgencyServiceTest {
    @Mock
    private AgencyRepository agencyRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AgencyOfficeRepository agencyOfficeRepository;

    @Mock
    private BusRepository busRepository;

    @Mock
    private PaymentRepository paymentRepository;


    @InjectMocks
    private AgencyServiceImpl agencyService;
}
