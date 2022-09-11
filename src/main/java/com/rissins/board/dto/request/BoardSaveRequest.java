package com.rissins.board.dto.request;

import com.rissins.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequest {

    private String name;

    public Board fromEntity() {
        if (name == null) {
            throw new IllegalArgumentException("입력된 내용이 없습니다.");
        }
        return Board.builder()
                .name(name)
                .build();
    }
}
