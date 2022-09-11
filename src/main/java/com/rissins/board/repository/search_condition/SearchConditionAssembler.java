package com.rissins.board.repository.search_condition;

import com.rissins.board.dto.request.SearchRequest;
import com.rissins.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchConditionAssembler {
    private final BoardService boardService;

    public SearchCondition fromSearchRequest(SearchRequest searchRequest) {
        List<String> boardNameWithFilterNames = new ArrayList<>();
        if (searchRequest.getBoardName() != null) {
            boardNameWithFilterNames = boardService.findBoardNameWithFilterName(searchRequest.getBoardName());
        }
        return SearchCondition.builder()
                .startDateTime(searchRequest.getStartDateTime())
                .endDateTime(searchRequest.getEndDateTime())
                .boardNames(boardNameWithFilterNames)
                .build();
    }
}
