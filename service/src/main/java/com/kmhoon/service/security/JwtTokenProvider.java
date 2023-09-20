package com.kmhoon.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtKey jwtKey;
    private final JwtTime jwtTime;

    public TokenInfo generate(Authentication authentication) {

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + jwtTime.getAccessTokenTime());

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(jwtKey.getKey(), SignatureAlgorithm.HS512)
                .compact();

        Date refreshExpiresIn = new Date(now + jwtTime.getRefreshTokenTime());

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(refreshExpiresIn)
                .signWith(jwtKey.getKey(), SignatureAlgorithm.HS512)
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
