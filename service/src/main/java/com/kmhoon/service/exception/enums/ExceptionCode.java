package com.kmhoon.service.exception.enums;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    Integer getCode();
    HttpStatus getStatus();
    String getMessage();

}
