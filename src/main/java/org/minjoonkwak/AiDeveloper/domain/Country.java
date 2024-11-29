package org.minjoonkwak.AiDeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Country") // 테이블 이름 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 제한
@Getter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT에 매핑
    @Column(name = "id", updatable = false) // ID는 수정 불가
    private Long id;

    @Column(name = "user_id", nullable = false) // user_id는 필수
    private String userId;

    @Column(name = "character_type", nullable = false) // character_type은 필수
    private String characterType;

    @Builder
    public Country(String userId, String characterType) {
        this.userId = userId;
        this.characterType = characterType;
    }
}
