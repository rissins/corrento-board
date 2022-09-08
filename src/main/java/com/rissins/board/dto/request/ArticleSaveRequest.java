package com.rissins.board.dto.request;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Board;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleSaveRequest {

    private Long boardId;
    private String title;
    private String content;
    private List<String> locations;

    public Article toEntity() {
        return Article.builder()
                .content(content)
                .title(title)
                .build();
    }
}
