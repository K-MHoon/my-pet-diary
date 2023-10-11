package com.kmhoon.service.exception;

import com.kmhoon.service.exception.enums.common.CommonExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DiaryServiceException.class })
    protected ResponseEntity<ExceptionDto> handleDiaryServiceException(DiaryServiceException ex) {
        log.error("handleDiaryServiceException = ", ex);
        return new ResponseEntity<>(ExceptionDto.of(ex.getExceptionCode()), ex.getExceptionCode().getStatus());
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<ExceptionDto> handleException(Exception ex) {
        CommonExceptionCode serverException = CommonExceptionCode.INTERNAL_SERVER_EXCEPTION;
        log.error("handleException = ", ex);
        return new ResponseEntity<>(ExceptionDto.of(serverException), serverException.getStatus());
    }
}
