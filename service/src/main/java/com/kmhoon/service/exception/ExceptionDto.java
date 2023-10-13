package com.kmhoon.service.exception;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExceptionDto {

    private Integer code;
    private String message;

    public static ExceptionDto of(ExceptionCode ex) {
        return ExceptionDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
    }
}
