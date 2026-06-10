package com.example.hospital.service;

import com.example.hospital.entity.User;
import com.example.hospital.entity.Role;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {

        if (user.getRole() == null) {
            user.setRole(Role.PATIENT);
        }

        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }
}