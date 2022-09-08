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
        return Board.builder()
                .name(name)
                .build();
    }
}
