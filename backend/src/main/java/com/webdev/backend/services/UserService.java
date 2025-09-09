package com.webdev.backend.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webdev.backend.entity.User;
import com.webdev.backend.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;

        createTestUser();
    }

    private void createTestUser() {
        User userTest = new User("abc123", "abc123@gmail.com", "mypass123");
        userRepo.save(userTest);
    }

    public User getUser(String username) {
        return userRepo.findByUsername(username).orElseThrow();
    }
}
