package com.kmhoon.service.exception.enums.entity.user;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode  implements ExceptionCode {

    USER_EMAIL_NOT_FOUND(4000,"사용자 이메일 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final Integer code;
    private final String message;
    private final HttpStatus status;
}

