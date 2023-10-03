package com.kmhoon.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecurityProperties {

    @Value("${diary.security.ignore-pattern}")
    private String[] ignorePatterns;
}
