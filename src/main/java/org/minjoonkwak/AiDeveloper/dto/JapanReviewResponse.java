package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;

@Getter
public class JapanReviewResponse {

    private Long id;          // 리뷰 ID
    private String userId;    // 사용자 ID
    private String title;     // 리뷰 제목
    private String content;   // 리뷰 내용

    public JapanReviewResponse(Long id, String userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    // 추가: 직렬화를 위해 getter 메서드 필요
    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}