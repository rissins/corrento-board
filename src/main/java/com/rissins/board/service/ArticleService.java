package com.rissins.board.service;

import com.rissins.board.domain.Article;
import com.rissins.board.dto.response.ArticleDetailResponse;
import com.rissins.board.dto.response.ArticleResponse;
import com.rissins.board.exception.ArticleNotFoundException;
import com.rissins.board.exception.AttachmentNotFoundException;
import com.rissins.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();

    }

    public List<Article> findAllByCreatedDatetimeBetween(LocalDateTime start, LocalDateTime end) {
        return articleRepository.findAllByCreatedDatetimeBetween(start, end);
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException(id)
        );
    }
}
