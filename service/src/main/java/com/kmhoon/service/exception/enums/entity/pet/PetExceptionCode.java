package com.kmhoon.service.exception.enums.entity.pet;

import com.kmhoon.service.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PetExceptionCode implements ExceptionCode {

    PET_NOT_FOUND(4000,"해당하는 반려동물 정보가 없습니다.", HttpStatus.BAD_REQUEST);

    private final Integer code;
    private final String message;
    private final HttpStatus status;
}
