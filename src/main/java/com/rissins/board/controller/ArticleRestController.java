package com.rissins.board.controller;

import com.rissins.board.domain.Article;
import com.rissins.board.dto.request.ArticleSaveRequest;
import com.rissins.board.dto.request.ArticleUpdateRequest;
import com.rissins.board.dto.request.SearchRequest;
import com.rissins.board.repository.search_condition.SearchCondition;
import com.rissins.board.dto.response.ArticleDetailResponse;
import com.rissins.board.dto.response.ArticleResponse;
import com.rissins.board.repository.search_condition.SearchConditionAssembler;
import com.rissins.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
@Slf4j
public class ArticleRestController {

    private final ArticleService articleService;
    private final SearchConditionAssembler searchConditionAssembler;

    /**
     * 게시글 저장
     */
    @PostMapping
    public void save(@RequestBody ArticleSaveRequest articleSaveRequest) {
        Article article = articleSaveRequest.toEntity();
        articleService.save(articleSaveRequest.getBoardId(), article, articleSaveRequest.getLocations());
    }

    /**
     * 게시글 조회
     * - Paging 처리 : 기본 size = 30
     * - Querydsl 동적 쿼리 적용
     * - /search : 전체조회
     * - /search?boardName=게시판명 : 게시판으로 조회
     * - /search?startDateTime=시작날짜&endDateTime=종료날짜 : Date로 검색
     */
    @GetMapping("/search")
    public List<ArticleResponse> search(@PageableDefault(size = 30) Pageable pageable,
                                        SearchRequest searchRequest) {
        SearchCondition searchCondition = searchConditionAssembler.fromSearchRequest(searchRequest);
        List<Article> articles = articleService.search(searchCondition, pageable);

        return ArticleResponse.fromEntity(articles);
    }

    /**
     * 게시글 상세조회
     */
    @GetMapping("/{id}")
    public ArticleDetailResponse findArticleById(@PathVariable Long id) {
        Article article = articleService.findWithOptimisticLockByIdAndViewCountUpdate(id);

        return ArticleDetailResponse.fromEntity(article);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ArticleUpdateRequest articleUpdateRequest) {
        articleService.update(id, articleUpdateRequest.getTitle(), articleUpdateRequest.getContent());
    }
}
