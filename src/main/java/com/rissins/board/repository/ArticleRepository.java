package com.rissins.board.repository;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByCreatedDatetimeBetween(LocalDateTime start, LocalDateTime end);

    List<Article> findAllByBoard(Board board);

    List<Article> findByBoardIn(List<Board> boards);
}
