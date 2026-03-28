package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    List<Bus> findByOffice_OfficeId(Integer officeId);
}
