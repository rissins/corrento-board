package com.rissins.board.controller;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Attachment;
import com.rissins.board.domain.Board;
import com.rissins.board.dto.request.BoardSaveRequest;
import com.rissins.board.dto.response.ArticleResponse;
import com.rissins.board.service.ArticleService;
import com.rissins.board.service.AttachmentService;
import com.rissins.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {

    private final BoardService boardService;
    private final AttachmentService attachmentService;
    private final ArticleService articleService;

    @PostMapping
    public void save(@RequestBody BoardSaveRequest boardSaveRequest) {
        List<Attachment> attachments = new ArrayList<>();

        Board board = Board.builder()
                .name(boardSaveRequest.getName())
                .build();

        Article article = Article.builder()
                .title(boardSaveRequest.getTitle())
                .content(boardSaveRequest.getContent())
                .board(board)
                .build();

        for (String location : boardSaveRequest.getLocation()) {
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
    public List<ArticleResponse> findAll() {
        return articleService.findAll();
    }

//    @GetMapping
//    public void findAllByRegDateBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
//
//    }
}
