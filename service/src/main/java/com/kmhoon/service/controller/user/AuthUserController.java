package com.kmhoon.service.controller.user;

import com.kmhoon.service.controller.user.request.AuthUserControllerRequest;
import com.kmhoon.service.service.user.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 사용자 로그인 정보 생성
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth/user")
public class AuthUserController {

    private final AuthUserService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Valid AuthUserControllerRequest.Register request) {
        service.register(request.toServiceRequest());
    }
}
