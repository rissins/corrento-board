package com.rissins.board.service;

import com.rissins.board.domain.Article;
import com.rissins.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void save(Article article) {
        articleRepository.save(article);
    }
}
