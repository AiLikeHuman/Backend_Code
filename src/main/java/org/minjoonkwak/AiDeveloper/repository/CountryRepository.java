package org.minjoonkwak.AiDeveloper.repository;

import org.minjoonkwak.AiDeveloper.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByUserId(String userId);
}
