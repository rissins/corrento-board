package com.rissins.board.dto.response;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailResponse {

    private String name;
    private String title;
    private LocalDateTime createdDateTime;
    private List<String> location;

    public ArticleDetailResponse fromEntity(Article article) {
        return ArticleDetailResponse.builder()
                .name(article.getBoard().getName())
                .title(article.getTitle())
                .createdDateTime(article.getCreatedDatetime())
                .location(article.getAttachments()
                        .stream()
                        .map(Attachment::getLocation)
                        .collect(Collectors.toList()))
                .build();
    }
}
