package com.cg.busbooking.controller;

import com.cg.busbooking.entity.User;
import com.cg.busbooking.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller responsible for handling authentication-related operations.
 * This controller exposes endpoints for user login and delegates the
 * authentication logic to the LoginService.
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    /**
     * Service layer dependency for login-related operations.
     */
    private final LoginService loginService;

    /**
     * Constructor-based dependency injection for LoginService.
     *
     * @param loginService service used to validate user credentials
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Authenticates a user based on provided username and password.
     * Endpoint: POST /auth/login
     * @param request User object containing username and password
     * @return ResponseEntity containing:
     *         - HTTP 200 (OK) with User details if authentication is successful
     *         - HTTP 401 (UNAUTHORIZED) if credentials are invalid
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User request) {

        User user = loginService.validateUser(
                request.getUsername(),
                request.getPassword()
        );

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}