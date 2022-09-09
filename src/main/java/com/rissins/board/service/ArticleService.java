package com.rissins.board.service;

import com.rissins.board.domain.Article;
import com.rissins.board.domain.Attachment;
import com.rissins.board.domain.Board;
import com.rissins.board.exception.ArticleNotFoundException;
import com.rissins.board.exception.ArticleUpdateContentDuplicateException;
import com.rissins.board.repository.ArticleRepository;
import com.rissins.board.repository.search_condition.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardService boardService;

    @Transactional
    public void save(Long boardId, Article article, List<String> locations) {
        Board board = boardService.findById(boardId);
        article.addBoard(board);
        List<Attachment> attachments = locations.stream()
                .map(location ->
                        Attachment.builder()
                                .article(article)
                                .location(location)
                                .build())
                .collect(Collectors.toList());
        article.addAttachments(attachments);
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException(id)
        );
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String title, String content) {
        Article article = findById(id);
        if (validContentDuplication(article.getContent(), content)) {
            throw new ArticleUpdateContentDuplicateException(id);
        }
        article.updateTitleAndContent(title, content);
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> search(SearchCondition searchCondition, Pageable pageable) {
        //게시판명의 일부분으로 검색된 게시판명
        List<String> boardNameWithFilterName = boardService.findBoardNameWithFilterName(searchCondition.getBoardName());
        searchCondition.addBoardNames(boardNameWithFilterName);
        return articleRepository.search(searchCondition, pageable);
    }

    @Transactional
    public Article findWithOptimisticLockByIdAndViewCountUpdate(Long id) {
        //낙관적락 적용
        Article article = articleRepository.findWithOptimisticLockById(id).orElseThrow(() ->
                new ArticleNotFoundException(id)
        );

        //조회수 증가 및 적용
        int viewCount = article.getViewCount();
        viewCount++;
        article.updateViewCount(viewCount);
        return article;
    }

    private Boolean validContentDuplication(String beforeContent, String updateContent) {
        return beforeContent.equals(updateContent);
    }
}
