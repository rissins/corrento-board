package com.rissins.board.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ArticleResponse {
    private String name;
    private String title;
    private LocalDateTime createdDateTime;
    private String location;
}
