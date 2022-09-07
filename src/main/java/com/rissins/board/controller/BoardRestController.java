package com.rissins.board.controller;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Attachment;
import com.rissins.board.domain.Board;
import com.rissins.board.dto.request.BoardSaveRequest;
import com.rissins.board.service.ArticleService;
import com.rissins.board.service.AttachmentService;
import com.rissins.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {

    private final BoardService boardService;
    private final AttachmentService attachmentService;
    private final ArticleService articleService;

    @PostMapping
    public void save(@RequestBody BoardSaveRequest boardSaveRequest) {
        Board board = Board.builder()
                .name(boardSaveRequest.getName())
                .build();

        Article article = Article.builder()
                .title(boardSaveRequest.getTitle())
                .content(boardSaveRequest.getContent())
                .board(board)
                .build();

        Attachment attachment = Attachment.builder()
                .article(article)
                .location(boardSaveRequest.getLocation())
                .build();

        boardService.save(board);
        articleService.save(article);
        attachmentService.save(attachment);
    }
}
