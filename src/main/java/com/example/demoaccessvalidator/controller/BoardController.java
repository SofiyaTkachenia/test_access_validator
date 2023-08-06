package com.example.demoaccessvalidator.controller;

import com.example.demoaccessvalidator.entity.Board;
import com.example.demoaccessvalidator.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board, @RequestHeader("userId") UUID userId) {
        Board createdBoard = boardService.createBoard(board, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable UUID id, @RequestHeader("userId") UUID userId) {
        Board board = boardService.getBoardById(id, userId);
        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable UUID id, @RequestHeader("userId") UUID userId) {
        boardService.deleteBoard(id, userId);
        return ResponseEntity.ok().build();
    }
}
