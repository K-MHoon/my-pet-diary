package com.kmhoon.service.exception.enums.entity.refrigerator;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RefrigeratorExceptionCode  implements ExceptionCode {

    REFRIGERATOR_NOT_FOUND(4000,"냉장고 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final Integer code;
    private final String message;
    private final HttpStatus status;
}
