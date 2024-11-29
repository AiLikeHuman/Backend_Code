package org.minjoonkwak.AiDeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "korea_review") // 테이블 이름 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 제한
@Getter
public class korea_review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT에 매핑
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public korea_review(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
