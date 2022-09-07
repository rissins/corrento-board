package com.rissins.board.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class BoardSaveRequest {

    private String name;
    private String title;
    private String content;
    private int viewCount;
    private List<String> location;
    private LocalDateTime localDatetime;
}
