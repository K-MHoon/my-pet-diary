package com.kmhoon.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserGender {

    WOMAN("W"),
    MAN("M");

    private final String value;

    public static UserGender from(String code) {
        return Arrays.stream(UserGender.values())
                .filter(v -> v.getValue().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 성별은 존재하지 않습니다."));
    }
}
