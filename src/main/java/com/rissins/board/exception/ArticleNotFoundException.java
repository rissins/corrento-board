package com.rissins.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Article Not Found")
public class ArticleNotFoundException extends RuntimeException {
    private static final String MESSAGE = "게시물을 찾을 수 없습니다..";

    public ArticleNotFoundException(Long id) {
        super(MESSAGE + "게시글 아이디 : " + id);
    }
}
