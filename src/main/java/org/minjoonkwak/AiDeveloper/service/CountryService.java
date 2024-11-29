package org.minjoonkwak.AiDeveloper.service;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.domain.Country;
import org.minjoonkwak.AiDeveloper.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public Country saveCountry(String userId, String characterType) {
        Country country = Country.builder()
                .userId(userId)
                .characterType(characterType)
                .build();
        return countryRepository.save(country);
    }

    // 3. 특정 사용자의 Country 조회
    public List<Country> findByUserId(String userId) {
        return countryRepository.findByUserId(userId);
    }
}
