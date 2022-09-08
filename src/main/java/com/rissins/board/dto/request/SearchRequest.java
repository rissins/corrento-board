package com.rissins.board.dto.request;

import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchRequest {

    @Nullable
    private LocalDateTime startDateTime;
    @Nullable
    private LocalDateTime endDateTime;
    @Nullable
    private String boardName;
}
