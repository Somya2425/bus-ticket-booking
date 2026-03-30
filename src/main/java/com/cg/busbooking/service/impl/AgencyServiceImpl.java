package com.cg.busbooking.service.impl;

import com.cg.busbooking.dto.response.AgencyRevenueDto;
import com.cg.busbooking.entity.Agency;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.repository.*;
import com.cg.busbooking.service.AgencyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    private final CustomerRepository customerRepository;
    private final AgencyRepository agencyRepository;
    private final AgencyOfficeRepository agencyOfficeRepository;
    private final BusRepository busRepository;
    private final PaymentRepository paymentRepository;

    public AgencyServiceImpl(CustomerRepository customerRepository, AgencyRepository agencyRepository, AgencyOfficeRepository agencyOfficeRepository, BusRepository busRepository, PaymentRepository paymentRepository) {
        this.customerRepository = customerRepository;
        this.agencyRepository = agencyRepository;
        this.agencyOfficeRepository = agencyOfficeRepository;
        this.busRepository = busRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Customer> getCustomersByAgencyId(Integer agencyId) {
        List<Customer> customers = new ArrayList<>();
        Optional<Agency> op = agencyRepository.findById(agencyId);
        if(op.isPresent()) {
            customers=customerRepository.findCustomerByAgencyId(agencyId);
        }
        return customers;
    }

    @Override
    public List<AgencyOffice> getOfficesByAgencyId(Integer id) {
        List<AgencyOffice> offices = new ArrayList<>();
        Optional<Agency> op = agencyRepository.findById(id);
        if(op.isPresent()) {
            offices=agencyOfficeRepository.findByAgencyId(id);
        }
        return offices;
    }

    @Override
    public List<Bus> getBusByAgencyIdAndDate(Integer agencyId, LocalDateTime tripDate) {
        List<Bus> buses = new ArrayList<>();
        Optional<Agency> op = agencyRepository.findById(agencyId);
        if(op.isPresent()) {
            buses=busRepository.findBusesByAgencyIdAndDate(agencyId, tripDate);
        }
        return buses;
    }

    @Override
    public AgencyRevenueDto getAgencyRevenueByAgencyId(Integer agencyId) {
        Optional<Agency> op = agencyRepository.findById(agencyId);
        if(op.isPresent()) {
            AgencyRevenueDto revenueDto = new AgencyRevenueDto();
            revenueDto.setAgencyName(op.get().getName());
            revenueDto.setRevenue(paymentRepository.findRevenueByAgencyId(agencyId).getRevenue());
            revenueDto.setAgencyId(op.get().getAgencyId());
            return revenueDto;
        }
        return null;
    }
}
