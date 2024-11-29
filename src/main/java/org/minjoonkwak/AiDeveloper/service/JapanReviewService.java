package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.japan_review;
import org.minjoonkwak.AiDeveloper.repository.JapanReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JapanReviewService {

    private final JapanReviewRepository japanReviewRepository;

    // 리뷰 저장
    public japan_review saveReview(String userId, String title, String content) {
        System.out.println("Saving review with UserId: " + userId);
        System.out.println("Review Title: " + title);
        System.out.println("Review Content: " + content);

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty");
        }

        if (title == null || content == null) {
            throw new IllegalArgumentException("Title and content cannot be null");
        }

        japan_review review = japan_review.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
        return japanReviewRepository.save(review);
    }

    // 모든 리뷰 조회
    public List<japan_review> findAllReviews() {
        return japanReviewRepository.findAll();
    }

    // 특정 사용자의 리뷰 조회
    public List<japan_review> findReviewsByUserId(String userId) {
        return japanReviewRepository.findByUserId(userId);
    }
}
