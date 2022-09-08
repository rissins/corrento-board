package com.rissins.board.controller;

import com.rissins.board.domain.Article;
import com.rissins.board.dto.request.ArticleSaveRequest;
import com.rissins.board.dto.request.ArticleUpdateRequest;
import com.rissins.board.dto.request.SearchRequest;
import com.rissins.board.repository.search_condition.SearchCondition;
import com.rissins.board.dto.response.ArticleDetailResponse;
import com.rissins.board.dto.response.ArticleResponse;
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

    @PostMapping
    public void save(@RequestBody ArticleSaveRequest articleSaveRequest) {
        Article article = articleSaveRequest.toEntity();
        articleService.save(articleSaveRequest.getBoardId(), article, articleSaveRequest.getLocations());
    }

    @GetMapping("/search")
    public List<ArticleResponse> search(@PageableDefault(size = 30) Pageable pageable,
                                        SearchRequest searchRequest) {
        SearchCondition searchCondition = searchRequest.toSearchCondition();

        List<Article> articles = articleService.search(searchCondition, pageable);

        return ArticleResponse.fromEntity(articles);
    }

    @GetMapping("/{id}")
    public ArticleDetailResponse findArticleById(@PathVariable Long id) {
        Article article = articleService.findWithOptimisticLockByIdAndViewCountUpdate(id);

        return ArticleDetailResponse.fromEntity(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ArticleUpdateRequest articleUpdateRequest) {
        articleService.update(id, articleUpdateRequest.getTitle(), articleUpdateRequest.getContent());
    }
}
