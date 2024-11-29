package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.korea_review;
import org.minjoonkwak.AiDeveloper.repository.KoreaReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KoreaReviewService {

    private final KoreaReviewRepository koreaReviewRepository;

    // 리뷰 저장
    public korea_review saveReview(String userId, String title, String content) {
        System.out.println("Saving review with UserId: " + userId);
        System.out.println("Review Title: " + title);
        System.out.println("Review Content: " + content);

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty");
        }

        if (title == null || content == null) {
            throw new IllegalArgumentException("Title and content cannot be null");
        }

        korea_review review = korea_review.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
        return koreaReviewRepository.save(review);
    }

    // 모든 리뷰 조회
    public List<korea_review> findAllReviews() {
        return koreaReviewRepository.findAll();
    }

    // 특정 사용자의 리뷰 조회
    public List<korea_review> findReviewsByUserId(String userId) {
        return koreaReviewRepository.findByUserId(userId);
    }
}