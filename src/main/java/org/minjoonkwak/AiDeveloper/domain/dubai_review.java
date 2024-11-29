package org.minjoonkwak.AiDeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dubai_review") // 테이블 이름 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 제한
@Getter
public class dubai_review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT에 매핑
    @Column(name = "id", updatable = false) // ID는 수정 불가
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public dubai_review(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
