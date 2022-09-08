package com.rissins.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rissins.board.domain.Article;
import com.rissins.board.repository.search_condition.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.rissins.board.domain.QArticle.article;

@RequiredArgsConstructor
@Component
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Article> search(SearchCondition searchCondition, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (searchCondition.getBoardName() != null) {
            booleanBuilder.and(article.board.name.eq(searchCondition.getBoardName()));
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
