package com.rissins.board.dto.response;

import com.rissins.board.domain.Article;
import com.rissins.board.exception.AttachmentNotFoundException;
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
public class ArticleResponse {
    private String name;
    private String title;
    private LocalDateTime createdDateTime;
    private String location;

    public List<ArticleResponse> fromEntity(List<Article> articles) {
        return articles.stream()
                .map(article -> ArticleResponse.builder()
                        .name(article.getBoard().getName())
                        .title(article.getTitle())
                        .createdDateTime(article.getCreatedDatetime())
                        .location
                                (
                                        article.getAttachments()
                                                .stream()
                                                .findFirst()
                                                .orElseThrow(AttachmentNotFoundException::new)
                                                .getLocation()
                                )
                        .build())
                .collect(Collectors.toList());
    }
}
