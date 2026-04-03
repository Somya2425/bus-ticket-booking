package com.cg.busbooking.service;

import com.cg.busbooking.entity.User;

public interface LoginService {
    public User validateUser(String username, String password);
}
