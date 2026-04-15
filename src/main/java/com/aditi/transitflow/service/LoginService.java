package com.aditi.transitflow.service;

import com.aditi.transitflow.entity.User;

/**
 * Service interface for handling user authentication.
 * This interface defines the contract for validating user credentials.
 */
public interface LoginService {

    /**
     * Validates user credentials.
     * @param username the username of the user
     * @param password the password provided for authentication
     * @return User object if credentials are valid, otherwise null
     */
    User validateUser(String username, String password);
}