package com.kmhoon.service.service.user.request;

import com.kmhoon.common.enums.UserGender;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthUserServiceRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static final class Register {

        private String email;
        private String password;
        private String nickName;
        private Long age;
        private UserGender gender;
    }
}
