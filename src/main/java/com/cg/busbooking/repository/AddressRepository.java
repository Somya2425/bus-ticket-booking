package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing database operations on Address entity.
 * This interface extends JpaRepository to provide basic CRUD operations
 * such as save, find, update, and delete for Address records.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
