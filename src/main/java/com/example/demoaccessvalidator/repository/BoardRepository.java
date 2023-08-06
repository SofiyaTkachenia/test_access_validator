package com.example.demoaccessvalidator.repository;

import com.example.demoaccessvalidator.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {

}
