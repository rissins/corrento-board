package com.rissins.board.repository;

import com.rissins.board.domain.Article;
import com.rissins.board.repository.search_condition.SearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<Article> search(SearchCondition searchCondition, Pageable pageable);
}
