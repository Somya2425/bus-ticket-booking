package com.cg.busbooking.service;

import com.cg.busbooking.dto.response.AgencyRevenueDto;
import com.cg.busbooking.entity.Agency;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.exception.ResourceNotFoundException;
import com.cg.busbooking.repository.*;
import com.cg.busbooking.service.impl.AgencyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    //get customers by agencyId:
    @Test
    void getCustomersByAgencyId_success() {
        Agency agency = new Agency();
        agency.setAgencyId(1);
        List<Customer> customers = List.of(new Customer(),new  Customer());

        when(agencyRepository.findById(1)).thenReturn(Optional.of(agency));
        when(customerRepository.findCustomerByAgencyId(1)).thenReturn(customers);

        List<Customer> result = agencyService.getCustomersByAgencyId(1);
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findCustomerByAgencyId(1);
    }

    @Test
    void getCustomersByAgencyId_notFound() {
        when(agencyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()-> agencyService.getCustomersByAgencyId(1));
    }

    //get offices of agency:
    @Test
    void getOfficesByAgencyId_success() {
        Agency agency = new Agency();
        agency.setAgencyId(1);
        List<AgencyOffice>  agencyOffices = List.of(new AgencyOffice(),new AgencyOffice());

        when(agencyRepository.findById(1)).thenReturn(Optional.of(agency));
        when(agencyOfficeRepository.findByAgencyId(1)).thenReturn(agencyOffices);

        List<AgencyOffice> result = agencyService.getOfficesByAgencyId(1);
        assertEquals(2, result.size());
        verify(agencyOfficeRepository, times(1)).findByAgencyId(1);
    }

    @Test
    void getOfficesByAgencyId_notFound() {
        when(agencyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()-> agencyService.getOfficesByAgencyId(1));
    }

    //get bus by agencyid and date:
    @Test
    void getBusByAgencyIdAndDate_success(){
        Agency agency = new Agency();
        agency.setAgencyId(1);

        LocalDateTime tripDate = LocalDateTime.now();
        List<Bus> busses = List.of(new Bus(),new Bus());

        when(agencyRepository.findById(1)).thenReturn(Optional.of(agency));
        when(busRepository.findBusesByAgencyIdAndDate(1,tripDate)).thenReturn(busses);

        List<Bus> result = agencyService.getBusByAgencyIdAndDate(1,tripDate);
        assertEquals(2, result.size());
        verify(busRepository, times(1)).findBusesByAgencyIdAndDate(1,tripDate);
    }

    @Test
    void getBusByAgencyIdAndDate_notFound() {
        LocalDateTime date = LocalDateTime.now();

        when(agencyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> agencyService.getBusByAgencyIdAndDate(1, date));
    }

    //get revenue of agency:
    @Test
    void getAgencyRevenueByAgencyId_success() {
        Agency agency = new Agency();
        agency.setAgencyId(1);
        agency.setName("ABC Travels");

        AgencyRevenueDto agencyRevenueDto = new AgencyRevenueDto(1, "ABC Travels", 5000.0);

        when(agencyRepository.findById(1)).thenReturn(Optional.of(agency));
        when(paymentRepository.findRevenueByAgencyId(1)).thenReturn(agencyRevenueDto);

        AgencyRevenueDto result = agencyService.getAgencyRevenueByAgencyId(1);

        assertEquals(1, result.getAgencyId());
        assertEquals("ABC Travels", result.getAgencyName());
        assertEquals(5000.0, result.getRevenue());

        verify(paymentRepository, times(1)).findRevenueByAgencyId(1);
        verify(agencyRepository, times(1)).findById(1);
    }

    @Test
    void getAgencyRevenueByAgencyId_notFound() {
        when(agencyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()-> agencyService.getAgencyRevenueByAgencyId(1));
    }
}
