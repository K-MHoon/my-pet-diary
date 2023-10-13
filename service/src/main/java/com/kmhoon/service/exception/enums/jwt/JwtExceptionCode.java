package com.kmhoon.service.exception.enums.jwt;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtExceptionCode implements ExceptionCode {

    AUTH_HEADER_NOT_FOUND(2000,"인증 헤더가 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    TOKEN_VALIDATION_FAIL(2001,"토큰 검증에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_INFORMATION_NOT_FOUND(2002, "권한 정보가 없습니다.", HttpStatus.UNAUTHORIZED)
    ;

    private final Integer code;
    private final String message;
    private final HttpStatus status;
}