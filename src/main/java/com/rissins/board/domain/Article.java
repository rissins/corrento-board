package com.rissins.board.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicUpdate
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

    @ToString.Exclude
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public void updateContent(String updateContent) {
        this.content = updateContent;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public Boolean validContentDuplication(String updateContent) {
        if (updateContent == null) {
            throw new IllegalArgumentException("입력된 수정내용이  없습니다.");
        }
        return !this.content.equals(updateContent);
    }
}
