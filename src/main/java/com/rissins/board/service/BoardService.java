package com.rissins.board.service;

import com.rissins.board.domain.Board;
import com.rissins.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board findByName(String name) {
        return boardRepository.findByName(name);
    }

    public List<Board> findAllFilterName(String name) {
        return boardRepository.findAll().stream().filter(board -> board.getName().contains(name)).collect(Collectors.toList());
    }
}
