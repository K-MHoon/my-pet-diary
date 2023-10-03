package com.kmhoon.common.converter;

import com.kmhoon.common.enums.UserGender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class UserGenderConverter implements AttributeConverter<UserGender, String> {
    @Override
    public String convertToDatabaseColumn(UserGender gender) {
        if(gender == null) return null;
        return gender.getValue();
    }

    @Override
    public UserGender convertToEntityAttribute(String s) {
        if(!StringUtils.hasText(s)) {
            return null;
        }
        try {
            return UserGender.from(s);
        } catch (IllegalArgumentException e){
            log.error("failure to convert causer unexpected code {}", s, e);
            throw e;
        }
    }
}
