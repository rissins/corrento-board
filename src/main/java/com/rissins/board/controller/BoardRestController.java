package com.rissins.board.controller;

import com.rissins.board.domain.Board;
import com.rissins.board.dto.request.BoardSaveRequest;
import com.rissins.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Slf4j
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시판 저장
     */
    @PostMapping
    public void save(@RequestBody BoardSaveRequest boardSaveRequest) {
        Board board = boardSaveRequest.fromEntity();

        boardService.save(board);
    }
}
