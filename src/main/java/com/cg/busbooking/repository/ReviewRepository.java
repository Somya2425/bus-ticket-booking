package com.cg.busbooking.repository;

import com.cg.busbooking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing database operations on Review entity.
 * This interface extends JpaRepository to provide basic CRUD operations
 * such as create, read, update, and delete for Review records.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
