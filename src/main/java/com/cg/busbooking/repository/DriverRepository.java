package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {
    List<Driver> findByOffice_OfficeId(Integer officeId);
}
