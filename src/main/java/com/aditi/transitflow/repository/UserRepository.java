package com.aditi.transitflow.repository;

import com.aditi.transitflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * This interface extends JpaRepository, providing built-in
 * CRUD operations and database interaction methods for User.
 */
public interface UserRepository extends JpaRepository<User, String> {

}