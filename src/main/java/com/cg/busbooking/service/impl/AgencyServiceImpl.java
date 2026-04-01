package com.cg.busbooking.service.impl;

import com.cg.busbooking.dto.response.AgencyRevenueDto;
import com.cg.busbooking.entity.Agency;
import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.exception.agency.AgencyNotFoundException;
import com.cg.busbooking.repository.*;
import com.cg.busbooking.service.AgencyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Optional<Agency> op = agencyRepository.findById(agencyId);
        if(!op.isPresent()) {
          throw new AgencyNotFoundException("Agency not found");
        }
        return customerRepository.findCustomerByAgencyId(agencyId);
    }

    @Override
    public List<AgencyOffice> getOfficesByAgencyId(Integer id) {
        Optional<Agency> op = agencyRepository.findById(id);
        if(!op.isPresent()) {
            throw new AgencyNotFoundException("Agency not found");
        }
        return agencyOfficeRepository.findByAgencyId(id);
    }

    @Override
    public List<Bus> getBusByAgencyIdAndDate(Integer agencyId, LocalDateTime tripDate) {
        Optional<Agency> op = agencyRepository.findById(agencyId);
        if(!op.isPresent()) {
            throw new AgencyNotFoundException("Agency not found");
        }
        return busRepository.findBusesByAgencyIdAndDate(agencyId, tripDate);
    }

    @Override
    public AgencyRevenueDto getAgencyRevenueByAgencyId(Integer agencyId) {
        Optional<Agency> op = agencyRepository.findById(agencyId);
        if(!op.isPresent()) {
            throw new AgencyNotFoundException("Agency not found");
        }
        AgencyRevenueDto revenueDto = new AgencyRevenueDto();
        revenueDto.setAgencyName(op.get().getName());
        revenueDto.setRevenue(paymentRepository.findRevenueByAgencyId(agencyId).getRevenue());
        revenueDto.setAgencyId(op.get().getAgencyId());
        return revenueDto;
    }
}
