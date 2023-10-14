package com.kmhoon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum IsUse {

    YES("Y"), NO("N");

    private final String value;

    public static IsUse from(String code) {
        return Arrays.stream(IsUse.values())
                .filter(v -> v.getValue().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용 여부는 존재하지 않습니다."));
    }
}
