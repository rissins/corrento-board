package com.rissins.board.dto.request;

import com.rissins.board.domain.Article;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleSaveRequest {

    private Long boardId;
    @NotBlank(message = "입력된 제목이 없습니다.")
    private String title;
    @NotBlank(message = "입력된 내용이 없습니다.")
    private String content;
    private List<String> locations;

    public Article toEntity() {
        return Article.builder()
                .content(content)
                .title(title)
                .build();
    }
}
