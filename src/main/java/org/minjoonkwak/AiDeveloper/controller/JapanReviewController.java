package org.minjoonkwak.AiDeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.User;
import org.minjoonkwak.AiDeveloper.domain.japan_review;
import org.minjoonkwak.AiDeveloper.dto.JapanReviewRequest;
import org.minjoonkwak.AiDeveloper.dto.JapanReviewResponse;
import org.minjoonkwak.AiDeveloper.service.JapanReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/japan-reviews")
public class JapanReviewController {

    private final JapanReviewService japanReviewService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<JapanReviewResponse> saveReview(
            @AuthenticationPrincipal User authenticatedUser, // 커스텀 User 객체 사용
            @RequestBody JapanReviewRequest request) {

        if (authenticatedUser == null) {
            throw new IllegalStateException("User is not authenticated");
        }

        String userId = authenticatedUser.getUserId(); // 로그인된 사용자 ID 가져오기
        japan_review savedReview = japanReviewService.saveReview(userId, request.getTitle(), request.getContent());

        JapanReviewResponse response = new JapanReviewResponse(
                savedReview.getId(),
                savedReview.getUserId(),
                savedReview.getTitle(),
                savedReview.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 모든 리뷰 조회
    @GetMapping
    public ResponseEntity<List<JapanReviewResponse>> getAllReviews() {
        List<JapanReviewResponse> responses = japanReviewService.findAllReviews()
                .stream()
                .map(review -> new JapanReviewResponse(
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
    public ResponseEntity<List<JapanReviewResponse>> getReviewsByUserId(@PathVariable String userId) {
        List<JapanReviewResponse> responses = japanReviewService.findReviewsByUserId(userId)
                .stream()
                .map(review -> new JapanReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}