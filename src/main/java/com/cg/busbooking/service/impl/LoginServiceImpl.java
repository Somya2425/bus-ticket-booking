package com.cg.busbooking.service.impl;

import com.cg.busbooking.entity.User;
import com.cg.busbooking.repository.UserRepository;
import com.cg.busbooking.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User validateUser(String username, String password) {
        Optional<User> optional = userRepository.findById(username);

        if (optional.isPresent()) {
            User user = optional.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
