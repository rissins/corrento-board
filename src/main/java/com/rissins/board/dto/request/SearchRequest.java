package com.rissins.board.dto.request;

import com.rissins.board.repository.search_condition.SearchCondition;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchRequest {

    @Nullable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDateTime;
    @Nullable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDateTime;
    @Nullable
    private String boardName;

    public SearchCondition toSearchCondition() {
        return SearchCondition.builder()
                .boardName(boardName)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
    }
}
