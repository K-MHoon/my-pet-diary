package com.kmhoon.common.converter.gender;

import com.kmhoon.common.enums.PetGender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class PetGenderConverter implements AttributeConverter<PetGender, String> {
    @Override
    public String convertToDatabaseColumn(PetGender gender) {
        if(gender == null) return null;
        return gender.getValue();
    }

    @Override
    public PetGender convertToEntityAttribute(String s) {
        if(!StringUtils.hasText(s)) {
            return null;
        }
        try {
            return PetGender.from(s);
        } catch (IllegalArgumentException e){
            log.error("failure to convert causer unexpected code {}", s, e);
            throw e;
        }
    }
}
