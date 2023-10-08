package com.kmhoon.service.exception;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class DiaryServiceException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public DiaryServiceException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
