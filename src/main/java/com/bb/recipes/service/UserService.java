package com.bb.recipes.service;

import com.bb.recipes.entities.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);
}