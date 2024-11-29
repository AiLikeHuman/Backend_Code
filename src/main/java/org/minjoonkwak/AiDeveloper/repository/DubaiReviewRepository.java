package org.minjoonkwak.AiDeveloper.repository;

import org.minjoonkwak.AiDeveloper.domain.dubai_review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DubaiReviewRepository extends JpaRepository<dubai_review, Long> {
    List<dubai_review> findByUserId(String userId);
}
