package org.minjoonkwak.AiDeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Question") // 테이블 이름 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 제한
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT에 매핑
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "question1", nullable = false)
    private String question1;

    @Column(name = "question2", nullable = false)
    private String question2;

    @Builder
    public Question(String userId, String question1, String question2) {
        this.userId = userId;
        this.question1 = question1;
        this.question2 = question2;
    }
}
