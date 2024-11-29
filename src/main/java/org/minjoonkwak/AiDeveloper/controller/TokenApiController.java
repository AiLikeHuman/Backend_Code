package org.minjoonkwak.AiDeveloper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.dto.*;
import org.minjoonkwak.AiDeveloper.service.TokenService;
import org.minjoonkwak.AiDeveloper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }

    @PostMapping("/api/signup")
    public ResponseEntity<Void> registerUser(@RequestBody AddUserRequest request) {
        System.out.println("Received UserId: " + request.getUserId()); // 로그 추가
        System.out.println("Received Password: " + request.getPassword());
        userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인
    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        try {
            String accessToken = userService.authenticate(request.getUserId(), request.getPassword());
            return ResponseEntity.ok(new LoginResponse(accessToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("아이디 또는 비밀번호가 잘못되었습니다."));
        }
    }


}