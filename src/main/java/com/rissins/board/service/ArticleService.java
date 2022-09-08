package com.rissins.board.service;

import com.rissins.board.domain.Article;
import com.rissins.board.exception.ArticleNotFoundException;
import com.rissins.board.exception.ArticleUpdateContentDuplicateException;
import com.rissins.board.repository.ArticleRepository;
import com.rissins.board.repository.search_condition.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException(id)
        );
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String title, String content) {
        Article article = findById(id);
        if (validContentDuplication(article.getContent(), content)) {
            throw new ArticleUpdateContentDuplicateException(id);
        }
        article.updateTitleAndContent(title, content);
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> search(SearchCondition searchCondition) {
        return articleRepository.search(searchCondition);
    }

    private Boolean validContentDuplication(String beforeContent, String updateContent) {
        return beforeContent.equals(updateContent);
    }
}
