package com.cg.busbooking.service.impl;

import com.cg.busbooking.entity.Agency;
import com.cg.busbooking.entity.Customer;
import com.cg.busbooking.repository.AgencyRepository;
import com.cg.busbooking.repository.CustomerRepository;
import com.cg.busbooking.service.AgencyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    private final CustomerRepository customerRepository;
    private final AgencyRepository agencyRepository;

    public AgencyServiceImpl(CustomerRepository customerRepository, AgencyRepository agencyRepository) {
        this.customerRepository = customerRepository;
        this.agencyRepository = agencyRepository;
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
}
