package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;

@Getter
public class CountryResponse {
    private Long id;
    private String userId;
    private String characterType;

    public CountryResponse(Long id, String userId, String characterType) {
        this.id = id;
        this.userId = userId;
        this.characterType = characterType;
    }
}