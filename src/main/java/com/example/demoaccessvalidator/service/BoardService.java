package com.example.demoaccessvalidator.service;

import com.example.demoaccessvalidator.access_validator.AccessPolicy;
import com.example.demoaccessvalidator.access_validator.AccessValidator;
import com.example.demoaccessvalidator.entity.Board;
import com.example.demoaccessvalidator.repository.BoardRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.demoaccessvalidator.access_validator.BoardAction.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccessValidator accessValidator;
    public Board createBoard(Board board, UUID userId) {
        accessValidator.validateAccess(userId, board, CREATE_BOARD);
        return boardRepository.save(board);
    }

    public Board getBoardById(UUID id, UUID userId) {
        Board board = findBoardById(id);
        accessValidator.validateAccess(userId, board, GET_BOARD);
        return board;
    }

    public void deleteBoard(UUID id, UUID userId) {
        Board board = findBoardById(id);
        accessValidator.validateAccess(userId, board, DELETE_BOARD);
        boardRepository.deleteById(id);
    }

    private Board findBoardById(UUID boardId) {
       return boardRepository
                .findById(boardId)
                .orElseThrow(() -> new RuntimeException("Cannot find this board"));
    }
}
