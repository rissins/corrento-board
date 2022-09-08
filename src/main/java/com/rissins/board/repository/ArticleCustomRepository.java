package com.rissins.board.repository;

import com.rissins.board.domain.Article;
import com.rissins.board.repository.search_condition.SearchCondition;

import java.util.List;

public interface ArticleCustomRepository {
    List<Article> search(SearchCondition searchCondition);
}
