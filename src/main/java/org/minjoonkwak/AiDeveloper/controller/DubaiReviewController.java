package org.minjoonkwak.AiDeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.User;
import org.minjoonkwak.AiDeveloper.domain.dubai_review;
import org.minjoonkwak.AiDeveloper.dto.DubaiReviewRequest;
import org.minjoonkwak.AiDeveloper.dto.DubaiReviewResponse;
import org.minjoonkwak.AiDeveloper.service.DubaiReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dubai-reviews")
public class DubaiReviewController {

    private final DubaiReviewService dubaiReviewService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<DubaiReviewResponse> saveReview(
            @AuthenticationPrincipal User authenticatedUser,
            @RequestBody DubaiReviewRequest request) {

        if (authenticatedUser == null) {
            throw new IllegalStateException("User is not authenticated");
        }

        String userId = authenticatedUser.getUserId();
        dubai_review savedReview = dubaiReviewService.saveReview(userId, request.getTitle(), request.getContent());

        DubaiReviewResponse response = new DubaiReviewResponse(
                savedReview.getId(),
                savedReview.getUserId(),
                savedReview.getTitle(),
                savedReview.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DubaiReviewResponse>> getAllReviews() {
        List<DubaiReviewResponse> responses = dubaiReviewService.findAllReviews()
                .stream()
                .map(review -> new DubaiReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DubaiReviewResponse>> getReviewsByUserId(@PathVariable String userId) {
        List<DubaiReviewResponse> responses = dubaiReviewService.findReviewsByUserId(userId)
                .stream()
                .map(review -> new DubaiReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
