package com.kmhoon.common.converter.disease;

import com.kmhoon.common.enums.DiseaseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class DiseaseTypeConverter implements AttributeConverter<DiseaseType, String> {
    @Override
    public String convertToDatabaseColumn(DiseaseType diseaseType) {
        if(diseaseType == null) return null;
        return diseaseType.getValue();
    }

    @Override
    public DiseaseType convertToEntityAttribute(String s) {
        if(!StringUtils.hasText(s)) {
            return null;
        }
        try {
            return DiseaseType.from(s);
        } catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code {}", s, e);
            throw e;
        }
    }
}