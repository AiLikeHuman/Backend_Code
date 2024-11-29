package org.minjoonkwak.AiDeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.minjoonkwak.AiDeveloper.dto.CountryRequest;
import org.minjoonkwak.AiDeveloper.dto.CountryResponse;
import org.minjoonkwak.AiDeveloper.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/countries") // API의 기본 경로
public class CountryController {

    private final CountryService countryService;

    // 1. Country 저장
    @PostMapping
    public ResponseEntity<CountryResponse> saveCountry(@RequestBody CountryRequest request) {
        var savedCountry = countryService.saveCountry(request.getUserId(), request.getCharacterType());
        var response = new CountryResponse(savedCountry.getId(), savedCountry.getUserId(), savedCountry.getCharacterType());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2. 특정 사용자(userId)의 Country 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CountryResponse>> getCountriesByUserId(@PathVariable String userId) {
        var countries = countryService.findByUserId(userId);
        var response = countries.stream()
                .map(country -> new CountryResponse(country.getId(), country.getUserId(), country.getCharacterType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
