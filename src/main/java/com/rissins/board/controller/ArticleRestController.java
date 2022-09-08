package com.rissins.board.controller;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Attachment;
import com.rissins.board.domain.Board;
import com.rissins.board.dto.request.ArticleSaveRequest;
import com.rissins.board.dto.request.ArticleUpdateRequest;
import com.rissins.board.dto.request.SearchRequest;
import com.rissins.board.repository.search_condition.SearchCondition;
import com.rissins.board.dto.response.ArticleDetailResponse;
import com.rissins.board.dto.response.ArticleResponse;
import com.rissins.board.service.ArticleService;
import com.rissins.board.service.AttachmentService;
import com.rissins.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
@Slf4j
public class ArticleRestController {

    private final BoardService boardService;
    private final AttachmentService attachmentService;
    private final ArticleService articleService;

    @PostMapping
    public void save(@RequestBody ArticleSaveRequest articleSaveRequest) {
        //save안에 넣기
        Board board = boardService.findById(articleSaveRequest.getBoardId());

        Article article = articleSaveRequest.toEntity(board);
        List<Attachment> attachments = articleSaveRequest.getLocations()
                .stream()
                .map(location ->
                        Attachment.builder()
                                .article(article)
                                .location(location)
                                .build())
                .collect(Collectors.toList());

        article.addAttachments(attachments);
        articleService.save(article);
    }

    @GetMapping("/search")
    public List<ArticleResponse> search(SearchRequest searchRequest) {
        //페이징 추가
        SearchCondition searchCondition = searchRequest.toSearchCondition();

        List<Article> articles = articleService.search(searchCondition);

        return ArticleResponse.fromEntity(articles);
    }

    @GetMapping("/{id}")
    public ArticleDetailResponse findArticleById(@PathVariable Long id) {
        //낙관적락 적용하기
        //조회수 오르기 추가
        Article article = articleService.findById(id);

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
