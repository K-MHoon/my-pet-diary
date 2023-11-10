package com.kmhoon.service.service.user.response;

import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.log.LoginLog;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public final class GetLoginLogListResponse {

    private List<LoginLogDto> loginLogDtoList = new ArrayList<>();

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @ToString
    public static class LoginLogDto {
        private Long id;
        private String email;
        private LoginStatus loginStatus;
        private LocalDateTime createdAt;

        public static LoginLogDto of(LoginLog loginLog) {
            return LoginLogDto.builder()
                    .id(loginLog.getId())
                    .email(loginLog.getLoginEmail())
                    .loginStatus(loginLog.getLoginStatus())
                    .createdAt(loginLog.getCreatedAt())
                    .build();
        }
    }
}
