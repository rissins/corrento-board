package com.rissins.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSaveRequest {

    private String name;
    private String title;
    private String content;
    private int viewCount;
    private List<String> location;
//    private LocalDateTime localDatetime;
}
