package com.cg.busbooking.service;

import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.RouteRepository;
import com.cg.busbooking.service.impl.RouteServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {
    @Mock
    private RouteRepository routeRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private RouteServiceImpl routeServiceImpl;
}
