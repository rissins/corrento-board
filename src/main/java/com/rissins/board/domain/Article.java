package com.rissins.board.domain;

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

    public void updateTitleAndContent(String title, String content) {
        if (title == null || content == null) {
            throw new IllegalArgumentException("입력된 수정내용이 없습니다.");
        }
        this.title = title;
        this.content = content;
    }

    public void updateViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
