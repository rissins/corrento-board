package com.rissins.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateRequest {

    @NotBlank(message = "수정제목이 공백입니다.")
    private String title;
    @NotBlank(message = "수정내용이 공백입니다.")
    private String content;
}
