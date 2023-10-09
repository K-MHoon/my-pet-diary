package com.kmhoon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Topic {
    EAT("EAT"), // 식사
    LIFE("LIFE"), // 일상
    ACTIVITY("ACTIVITY") // 활동
    ;

    private final String value;
}
