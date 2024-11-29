package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {
    private String userId;       // 사용자 ID
    private String question1;    // 질문 1
    private String question2;    // 질문 2
}