package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

}
