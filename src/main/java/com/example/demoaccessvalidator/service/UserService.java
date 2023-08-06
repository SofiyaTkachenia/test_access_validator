package com.example.demoaccessvalidator.service;

import com.example.demoaccessvalidator.entity.Group;
import com.example.demoaccessvalidator.entity.User;
import com.example.demoaccessvalidator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        if (user.getName().isEmpty()) {
            throw new RuntimeException("User name cannot be empty");
        }
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find this user"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
