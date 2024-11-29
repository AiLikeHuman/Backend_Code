package org.minjoonkwak.AiDeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.User;
import org.minjoonkwak.AiDeveloper.domain.france_review;
import org.minjoonkwak.AiDeveloper.dto.FranceReviewRequest;
import org.minjoonkwak.AiDeveloper.dto.FranceReviewResponse;
import org.minjoonkwak.AiDeveloper.service.FranceReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/france-reviews")
public class FranceReviewController {

    private final FranceReviewService franceReviewService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<FranceReviewResponse> saveReview(
            @AuthenticationPrincipal User authenticatedUser,
            @RequestBody FranceReviewRequest request) {

        if (authenticatedUser == null) {
            throw new IllegalStateException("User is not authenticated");
        }

        String userId = authenticatedUser.getUserId();
        france_review savedReview = franceReviewService.saveReview(userId, request.getTitle(), request.getContent());

        FranceReviewResponse response = new FranceReviewResponse(
                savedReview.getId(),
                savedReview.getUserId(),
                savedReview.getTitle(),
                savedReview.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FranceReviewResponse>> getAllReviews() {
        List<FranceReviewResponse> responses = franceReviewService.findAllReviews()
                .stream()
                .map(review -> new FranceReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FranceReviewResponse>> getReviewsByUserId(@PathVariable String userId) {
        List<FranceReviewResponse> responses = franceReviewService.findReviewsByUserId(userId)
                .stream()
                .map(review -> new FranceReviewResponse(
                        review.getId(),
                        review.getUserId(),
                        review.getTitle(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
