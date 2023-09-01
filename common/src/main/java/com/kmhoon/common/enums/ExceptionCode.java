package com.kmhoon.common.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    PET_NOT_FOUND_EXCEPTION(1000,"해당하는 반려동물 정보가 없습니다.");

    ExceptionCode(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;
    private String value;
}
