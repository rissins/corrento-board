package com.rissins.board.service;

import com.rissins.board.domain.Board;
import com.rissins.board.exception.BoardNotFoundException;
import com.rissins.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new BoardNotFoundException(id)
        );
    }
}
