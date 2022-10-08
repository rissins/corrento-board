package com.rissins.board.repository;

import com.rissins.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    //낙관적락
    @Lock(LockModeType.OPTIMISTIC)
    Optional<Article> findWithOptimisticLockById(Long id);
}
