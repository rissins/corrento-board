package com.rissins.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Attachment Not Found")
public class AttachmentNotFoundException extends RuntimeException {
    private static final String MESSAGE = "첨부파일을 찾을 수 없습니다..";

    public AttachmentNotFoundException() {
        super(MESSAGE);
    }
}
