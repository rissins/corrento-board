package com.rissins.board.controller;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Attachment;
import com.rissins.board.domain.Board;
import com.rissins.board.dto.request.ArticleSaveRequest;
import com.rissins.board.dto.request.ArticleUpdateRequest;
import com.rissins.board.dto.request.SearchRequest;
import com.rissins.board.repository.ArticleCustomRepositoryImpl;
import com.rissins.board.repository.search_condition.SearchCondition;
import com.rissins.board.dto.response.ArticleDetailResponse;
import com.rissins.board.dto.response.ArticleResponse;
import com.rissins.board.service.ArticleService;
import com.rissins.board.service.AttachmentService;
import com.rissins.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
@Slf4j
public class ArticleRestController {

    private final BoardService boardService;
    private final AttachmentService attachmentService;
    private final ArticleService articleService;
    private final ArticleCustomRepositoryImpl articleCustomRepository;

    @PostMapping
    public void save(@RequestBody ArticleSaveRequest articleSaveRequest) {
        List<Attachment> attachments = new ArrayList<>();

        Board board = boardService.findByName(articleSaveRequest.getBoardName());

        Article article = Article.builder()
                .title(articleSaveRequest.getTitle())
                .content(articleSaveRequest.getContent())
                .board(board)
                .viewCount(articleSaveRequest.getViewCount())
                .build();

        for (String location : articleSaveRequest.getLocation()) {
            Attachment attachment = Attachment.builder()
                    .article(article)
                    .location(location)
                    .build();

            attachments.add(attachment);
            attachmentService.save(attachment);
        }

        boardService.save(board);

        article.addAttachment(attachments);
        articleService.save(article);
    }

    @GetMapping
    public List<ArticleResponse> search( SearchRequest searchRequest) {
        System.out.println("searchRequest.toString() = " + searchRequest.toString());
        SearchCondition searchCondition = SearchCondition.builder()
                .startDateTime(searchRequest.getStartDateTime())
                .endDateTime(searchRequest.getEndDateTime())
                .boardName(searchRequest.getBoardName())
                .build();

        List<Article> articles = articleCustomRepository.search(searchCondition);

        return ArticleResponse.fromEntity(articles);
    }

    @GetMapping("/{id}")
    public ArticleDetailResponse findArticleById(@PathVariable Long id) {
        return ArticleDetailResponse.fromEntity(articleService.findArticleById(id));
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
