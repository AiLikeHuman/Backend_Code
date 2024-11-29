package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.france_review;
import org.minjoonkwak.AiDeveloper.repository.FranceReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FranceReviewService {

    private final FranceReviewRepository franceReviewRepository;

    // 리뷰 저장
    public france_review saveReview(String userId, String title, String content) {
        System.out.println("Saving review with UserId: " + userId);
        System.out.println("Review Title: " + title);
        System.out.println("Review Content: " + content);

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty");
        }

        if (title == null || content == null) {
            throw new IllegalArgumentException("Title and content cannot be null");
        }

        france_review review = france_review.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
        return franceReviewRepository.save(review);
    }

    // 모든 리뷰 조회
    public List<france_review> findAllReviews() {
        return franceReviewRepository.findAll();
    }

    // 특정 사용자의 리뷰 조회
    public List<france_review> findReviewsByUserId(String userId) {
        return franceReviewRepository.findByUserId(userId);
    }
}