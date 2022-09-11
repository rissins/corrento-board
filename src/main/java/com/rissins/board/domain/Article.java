package com.rissins.board.domain;

import com.rissins.board.exception.ArticleUpdateContentDuplicateException;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int viewCount = 0;

    @Version
    private Long version;

    @CreatedDate
    private LocalDateTime createdDatetime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Board board;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Attachment> attachments = new ArrayList<>();

    public void addAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addBoard(Board board) {
        this.board = board;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void validContentDuplicationAndUpdate(Long id, String updateContent) {
        if (updateContent == null) {
            throw new IllegalArgumentException("입력된 수정내용이  없습니다.");
        }

        if (this.content.equals(updateContent)) {
            throw new ArticleUpdateContentDuplicateException(id);
        } else {
            this.content = updateContent;
        }
    }
}
