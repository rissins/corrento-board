package com.rissins.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Update Article Content Duplicated")
public class ArticleUpdateContentDuplicateException extends RuntimeException {
    private static final String MESSAGE = "똑같은 내용으로 수정이 불가능합니다.";

    public ArticleUpdateContentDuplicateException(Long id) {
        super(MESSAGE + " 게시글 아이디 : " + id);
    }
}
