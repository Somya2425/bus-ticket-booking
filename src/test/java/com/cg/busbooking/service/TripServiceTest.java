package com.cg.busbooking.service;

import com.cg.busbooking.repository.TripRepository;
import com.cg.busbooking.service.impl.TripServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripServiceImpl;
}
