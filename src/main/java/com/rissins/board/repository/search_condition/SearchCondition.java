package com.rissins.board.repository.search_condition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String boardName;

}
