package org.minjoonkwak.AiDeveloper.repository;

import org.minjoonkwak.AiDeveloper.domain.france_review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FranceReviewRepository extends JpaRepository<france_review, Long> {
    List<france_review> findByUserId(String userId);
}
