package com.rissins.board.repository.search_condition;

import com.rissins.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String boardName;
    private List<String> boardNames;

    public void addBoardNames(List<String> boardNames) {
        this.boardNames = boardNames;
    }
}
