package com.rissins.board.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardSaveRequest {

    private String name;
    private String title;
    private String content;
    private int viewCount;
    private String location;
    private LocalDateTime localDateTime;
}
