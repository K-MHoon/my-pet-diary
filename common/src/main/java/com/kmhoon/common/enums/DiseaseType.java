package com.kmhoon.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DiseaseType {

    SERIOUS("S"),
    AVERAGE("A"),
    WEAK("W");

    private final String value;

    public static DiseaseType from(String code) {
        return Arrays.stream(DiseaseType.values())
                .filter(v -> v.getValue().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 질병 타입은 존재하지 않습니다."));
    }
}
