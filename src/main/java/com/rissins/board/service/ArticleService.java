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
    public void update(Long id, String content, String title) {
        Article article = findById(id);
        article.updateTitle(title);
        if (article.validContentDuplication(content)) {
            article.updateContent(content);
        } else {
            throw new ArticleUpdateContentDuplicateException(id);
        }
    }

    @Transactional(readOnly = true)
    public List<Article> search(SearchCondition searchCondition, Pageable pageable) {
        return articleRepository.search(searchCondition, pageable);
    }

    @Transactional
    public Article findWithOptimisticLockByIdAndIncreaseViewCount(Long id) {
        //???????????? ??????
        Article article = articleRepository.findWithOptimisticLockById(id).orElseThrow(() ->
                new ArticleNotFoundException(id)
        );

        //????????? ?????? ??? ??????
        article.increaseViewCount();
        return article;
    }
}
