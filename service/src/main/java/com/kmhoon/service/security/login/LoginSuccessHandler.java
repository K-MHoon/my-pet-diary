package com.kmhoon.service.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.LoginLog;
import com.kmhoon.common.repository.log.LoginLogRepository;
import com.kmhoon.service.security.jwt.JwtTokenProvider;
import com.kmhoon.service.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final LoginLogRepository loginLogRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        TokenInfo tokenInfo = jwtTokenProvider.generate(authentication);
        String tokenString = objectMapper.writeValueAsString(tokenInfo);
        loginLogRepository.save(LoginLog.builder()
                        .loginEmail(authentication.getName())
                        .loginStatus(LoginStatus.SUCCESS)
                .build());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(tokenString);
    }
}
