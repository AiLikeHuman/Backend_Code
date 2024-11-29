package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;

@Getter
public class QuestionResponse {
    private Long id;            // 질문 ID
    private String userId;      // 사용자 ID
    private String question1;   // 질문 1
    private String question2;   // 질문 2

    public QuestionResponse(Long id, String userId, String question1, String question2) {
        this.id = id;
        this.userId = userId;
        this.question1 = question1;
        this.question2 = question2;
    }
}
