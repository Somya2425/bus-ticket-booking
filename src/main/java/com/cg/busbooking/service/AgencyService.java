package com.cg.busbooking.service;

import com.cg.busbooking.entity.AgencyOffice;
import com.cg.busbooking.entity.Bus;
import com.cg.busbooking.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;

public interface AgencyService {
    List<Customer> getCustomersByAgencyId(Integer id);
    List<AgencyOffice> getOfficesByAgencyId(Integer id);
    List<Bus> getBusByAgencyIdAndDate(Integer agencyId, LocalDateTime tripDate);
}
