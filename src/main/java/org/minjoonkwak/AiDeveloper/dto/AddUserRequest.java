package org.minjoonkwak.AiDeveloper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {

    @Schema(description = "사용자의 이메일", example = "user@example.com")
    private String email;

    @Schema(description = "사용자의 비밀번호", example = "password123")
    private String password;
}