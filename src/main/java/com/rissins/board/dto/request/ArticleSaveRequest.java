package com.rissins.board.dto.request;

import com.rissins.board.domain.Article;
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
        if (title == null || content == null) {
            throw new IllegalArgumentException("입력된 내용이 없습니다.");
        }
        return Article.builder()
                .content(content)
                .title(title)
                .build();
    }
}
