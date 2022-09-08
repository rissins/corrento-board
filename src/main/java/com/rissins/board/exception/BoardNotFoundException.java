package com.rissins.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Board Not Found")
public class BoardNotFoundException extends RuntimeException {
    private static final String MESSAGE = "게시판을 찾을 수 없습니다..";

    public BoardNotFoundException(Long id) {
        super(MESSAGE + "게시판 아이디 : " + id);
    }
}
