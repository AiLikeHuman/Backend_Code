package org.minjoonkwak.AiDeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.dto.QuestionResponse;
import org.minjoonkwak.AiDeveloper.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions") // API 기본 경로
public class QuestionController {

    private final QuestionService questionService;

    // 3. 특정 사용자(userId)의 질문 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByUserId(@PathVariable String userId) {
        var questions = questionService.findByUserId(userId);
        var response = questions.stream()
                .map(question -> new QuestionResponse(
                        question.getId(),
                        question.getUserId(),
                        question.getQuestion1(),
                        question.getQuestion2()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}