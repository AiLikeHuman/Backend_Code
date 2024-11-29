package org.minjoonkwak.AiDeveloper.repository;

import org.minjoonkwak.AiDeveloper.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUserId(String userId);
}
