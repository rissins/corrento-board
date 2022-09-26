package com.rissins.board.dto.request;

import com.rissins.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequest {

    @NotBlank(message = "입력된 게시판 이름이 없습니다.")
    private String name;

    public Board fromEntity() {
        return Board.builder()
                .name(name)
                .build();
    }
}
