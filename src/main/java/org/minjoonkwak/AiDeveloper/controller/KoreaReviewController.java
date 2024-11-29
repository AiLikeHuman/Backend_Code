package org.minjoonkwak.AiDeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.User;
import org.minjoonkwak.AiDeveloper.domain.korea_review;
import org.minjoonkwak.AiDeveloper.dto.JapanReviewRequest;
import org.minjoonkwak.AiDeveloper.dto.JapanReviewResponse;
import org.minjoonkwak.AiDeveloper.dto.KoreaReviewRequest;
import org.minjoonkwak.AiDeveloper.dto.KoreaReviewResponse;
import org.minjoonkwak.AiDeveloper.service.JapanReviewService;
import org.minjoonkwak.AiDeveloper.service.KoreaReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/korea-reviews")
public class KoreaReviewController {

    private final KoreaReviewService koreaReviewService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<KoreaReviewResponse> saveReview(
            @AuthenticationPrincipal User authenticatedUser, // 커스텀 User 객체 사용
            @RequestBody KoreaReviewRequest request) {

        if (authenticatedUser == null) {
            throw new IllegalStateException("User is not authenticated");
        }

        String userId = authenticatedUser.getUserId(); // 로그인된 사용자 ID 가져오기
        korea_review savedReview = koreaReviewService.saveReview(userId, request.getTitle(), request.getContent());

        KoreaReviewResponse response = new KoreaReviewResponse(
                savedReview.getId(),
                savedReview.getUserId(),
                savedReview.getTitle(),
                savedReview.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 모든 리뷰 조회
    @GetMapping
    public ResponseEntity<List<KoreaReviewResponse>> getAllReviews() {
        List<KoreaReviewResponse> responses = koreaReviewService.findAllReviews()
                .stream()
                .map(review -> new KoreaReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // 특정 사용자의 리뷰 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<KoreaReviewResponse>> getReviewsByUserId(@PathVariable String userId) {
        List<KoreaReviewResponse> responses = koreaReviewService.findReviewsByUserId(userId)
                .stream()
                .map(review -> new KoreaReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
