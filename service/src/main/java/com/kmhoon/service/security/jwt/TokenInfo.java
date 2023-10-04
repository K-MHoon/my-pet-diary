package com.kmhoon.service.security.jwt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInfo {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}
