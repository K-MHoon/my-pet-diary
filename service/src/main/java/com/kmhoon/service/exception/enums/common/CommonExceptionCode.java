package com.kmhoon.service.exception.enums.common;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonExceptionCode implements ExceptionCode {

    INTERNAL_SERVER_EXCEPTION(5000, "서버 내부 오류 입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final Integer code;
    private final String message;
    private final HttpStatus status;
}
