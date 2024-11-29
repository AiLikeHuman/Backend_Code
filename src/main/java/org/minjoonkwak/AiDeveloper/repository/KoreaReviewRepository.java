package org.minjoonkwak.AiDeveloper.repository;

import org.minjoonkwak.AiDeveloper.domain.korea_review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoreaReviewRepository extends JpaRepository<korea_review, Long> {
    List<korea_review> findByUserId(String userId);
}
