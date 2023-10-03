package com.kmhoon.service.controller.user.request;

import com.kmhoon.common.enums.UserGender;
import com.kmhoon.service.service.user.request.AuthUserServiceRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthUserControllerRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static final class Register {

        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String nickName;

        @NotNull
        private Long age;

        @NotNull
        private UserGender gender;

        public AuthUserServiceRequest.Register toServiceRequest() {
            return AuthUserServiceRequest.Register.builder()
                    .email(this.email)
                    .password(this.password)
                    .nickName(this.nickName)
                    .age(this.age)
                    .gender(this.gender)
                    .build();
        }
    }
}
