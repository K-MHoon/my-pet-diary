package com.kmhoon.common.converter.use;

import com.kmhoon.common.enums.IsUse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class IsUseConverter  implements AttributeConverter<IsUse, String> {
    @Override
    public String convertToDatabaseColumn(IsUse gender) {
        if(gender == null) return null;
        return gender.getValue();
    }

    @Override
    public IsUse convertToEntityAttribute(String s) {
        if(!StringUtils.hasText(s)) {
            return null;
        }
        try {
            return IsUse.from(s);
        } catch (IllegalArgumentException e){
            log.error("failure to convert causer unexpected code {}", s, e);
            throw e;
        }
    }
}

