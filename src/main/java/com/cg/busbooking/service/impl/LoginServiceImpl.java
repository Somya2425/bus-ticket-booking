package com.cg.busbooking.service.impl;

import com.cg.busbooking.entity.User;
import com.cg.busbooking.repository.UserRepository;
import com.cg.busbooking.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for handling user authentication.
 *
 * This class implements the LoginService interface and provides
 * logic to validate user credentials by interacting with the database
 * through UserRepository.
 */
@Service
public class LoginServiceImpl implements LoginService {
    /**
     * Repository layer to support user related operations
     */
    private final UserRepository userRepository;

    /**
     * Constructor-based dependency injection for UserRepository.
     *
     * @param userRepository repository used to fetch user data from database
     */
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validates user credentials.
     * @param username the username of the user
     * @param password the password provided for authentication
     * @return User object if credentials are valid, otherwise null
     */
    @Override
    public User validateUser(String username, String password) {

        Optional<User> optional = userRepository.findById(username);

        // Check if user exists
        if (optional.isPresent()) {
            User user = optional.get();

            // Validate password
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        // Return null if authentication fails
        return null;
    }
}