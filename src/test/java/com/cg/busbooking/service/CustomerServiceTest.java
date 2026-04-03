package com.cg.busbooking.service;

import com.cg.busbooking.repository.BookingRepository;
import com.cg.busbooking.repository.CustomerRepository;
import com.cg.busbooking.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookingRepository bookingRepository;


    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;
}
