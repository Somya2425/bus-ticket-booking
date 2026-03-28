package com.cg.busbooking.service;

import com.cg.busbooking.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AgencyService {
    List<Customer> getCustomersByAgencyId(Integer id);
}
