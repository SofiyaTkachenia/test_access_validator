package com.example.demoaccessvalidator.repository;

import com.example.demoaccessvalidator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
