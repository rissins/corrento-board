package com.rissins.board.dto.request;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSaveRequest {

    private Long boardId;
    private String title;
    private String content;
    private List<String> locations;

    public Article toEntity(Board board) {
        return Article.builder()
                .board(board)
                .content(content)
                .title(title)
                .build();
    }
}
