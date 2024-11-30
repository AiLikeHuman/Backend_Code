package org.minjoonkwak.AiDeveloper.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    private String id; // 사용자 ID (Primary Key)

    private String email;
    private String password;
    private String nickname;
    private String role; // 사용자 역할 (예: USER, ADMIN)
}