package org.minjoonkwak.AiDeveloper.repository;

import org.minjoonkwak.AiDeveloper.domain.japan_review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JapanReviewRepository extends JpaRepository<japan_review, Long> {
    List<japan_review> findByUserId(String userId);
}
