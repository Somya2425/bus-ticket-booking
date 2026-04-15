package com.aditi.transitflow.repository;

import com.aditi.transitflow.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing database operations on Agency entity.
 * This interface extends JpaRepository to provide basic CRUD operations
 * such as create, read, update, and delete for Agency records.
 */
@Repository
public interface AgencyRepository extends JpaRepository<Agency,Integer> {
}
