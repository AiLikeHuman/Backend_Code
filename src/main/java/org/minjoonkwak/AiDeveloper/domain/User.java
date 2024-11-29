package org.minjoonkwak.AiDeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false) // PK, auto_increment
    private Long id;

    @Column(name = "created_at", updatable = false) // 생성일시
    private LocalDateTime createdAt;

    @Column(name = "updated_at") // 수정일시
    private LocalDateTime updatedAt;

    @Column(name = "user_id", nullable = false, unique = true) // 사용자 ID
    private String userId;

    @Column(name = "nickname") // 닉네임
    private String nickname;

    @Column(name = "password", nullable = false) // 비밀번호
    private String password;

    @Column(name = "kakao_id") // 카카오 ID
    private String kakaoId;

    @Column(name = "google_id") // 구글 ID
    private String googleId;

    @Builder
    public User(String userId, String password, String nickname, String kakaoId, String googleId) {
        System.out.println("Building User with UserId: " + userId); // 로그 추가
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.kakaoId = kakaoId;
        this.googleId = googleId;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}