package com.rissins.board.service;

import com.rissins.board.domain.Article;
import com.rissins.board.dto.response.ArticleResponse;
import com.rissins.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<ArticleResponse> findAll() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream().map(article -> ArticleResponse.builder()
                .name(article.getBoard().getName())
                .title(article.getTitle())
                .createdDateTime(article.getCreatedDatetime())
                .build()).collect(Collectors.toList());
    }
}
