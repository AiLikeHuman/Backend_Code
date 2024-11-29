package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequest {
    private String userId; // 사용자 ID
    private String characterType; // 캐릭터 타입
}
