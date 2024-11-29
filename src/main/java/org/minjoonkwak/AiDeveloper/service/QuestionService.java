package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.Question;
import org.minjoonkwak.AiDeveloper.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findByUserId(String userId) {
        return questionRepository.findByUserId(userId);
    }
}
