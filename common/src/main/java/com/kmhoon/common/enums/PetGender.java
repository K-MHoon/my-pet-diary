package com.kmhoon.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum PetGender {

    MALE("M"), FEMALE("F");

    private final String value;

    public static PetGender from(String code) {
        return Arrays.stream(PetGender.values())
                .filter(v -> v.getValue().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 동물 성별은 존재하지 않습니다."));
    }
}
