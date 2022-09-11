package com.rissins.board.service;

import com.rissins.board.domain.Board;
import com.rissins.board.exception.BoardNotFoundException;
import com.rissins.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> findBoardNameWithFilterName(String name) {
        return boardRepository.findAll()
                .stream()
                .map(Board::getName)
                .filter(boardName ->
                        boardName.contains(name))
                .collect(Collectors.toList());
    }
}
