package com.rissins.board.repository.search_condition;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchCondition {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<String> boardNames;
}
