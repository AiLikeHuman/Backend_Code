package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.config.jwt.TokenProvider;
import org.minjoonkwak.AiDeveloper.domain.User;
import org.minjoonkwak.AiDeveloper.dto.AddUserRequest;
import org.minjoonkwak.AiDeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    public Long save(AddUserRequest dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // 인증이 성공하면 액세스 토큰을 생성하여 반환
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

}