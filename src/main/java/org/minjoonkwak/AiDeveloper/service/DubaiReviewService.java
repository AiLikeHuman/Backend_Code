package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.dubai_review;
import org.minjoonkwak.AiDeveloper.repository.DubaiReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DubaiReviewService {

    private final DubaiReviewRepository dubaiReviewRepository;

    // 리뷰 저장
    public dubai_review saveReview(String userId, String title, String content) {
        System.out.println("Saving review with UserId: " + userId);
        System.out.println("Review Title: " + title);
        System.out.println("Review Content: " + content);

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty");
        }

        if (title == null || content == null) {
            throw new IllegalArgumentException("Title and content cannot be null");
        }

        dubai_review review = dubai_review.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
        return dubaiReviewRepository.save(review);
    }

    // 모든 리뷰 조회
    public List<dubai_review> findAllReviews() {
        return dubaiReviewRepository.findAll();
    }

    // 특정 사용자의 리뷰 조회
    public List<dubai_review> findReviewsByUserId(String userId) {
        return dubaiReviewRepository.findByUserId(userId);
    }
}
