package com.kmhoon.service.security.login;

import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.LoginLog;
import com.kmhoon.common.repository.log.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final LoginLogRepository loginLogRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String loginEmail = request.getParameter("username");
        loginLogRepository.save(LoginLog.builder()
                .loginEmail(loginEmail)
                .loginStatus(LoginStatus.FAILED)
                .build());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
