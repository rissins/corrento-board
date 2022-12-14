package com.rissins.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rissins.board.domain.Article;
import com.rissins.board.repository.search_condition.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rissins.board.domain.QArticle.article;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Article> search(SearchCondition searchCondition, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!searchCondition.getBoardNames().isEmpty()) {
            booleanBuilder.and(article.board.name.in(searchCondition.getBoardNames()));
        }

        if (searchCondition.getStartDateTime() != null && searchCondition.getEndDateTime() != null) {
            booleanBuilder.and(article.createdDatetime.between(searchCondition.getStartDateTime(), searchCondition.getEndDateTime()));
        }

        return queryFactory
                .selectFrom(article)
                .innerJoin(article.board)
                .fetchJoin()
                .where(
                        booleanBuilder
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
