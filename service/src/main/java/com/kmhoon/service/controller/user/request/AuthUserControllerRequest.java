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

        @NotBlank(message = "{auth-user.email.not-blank}")
        private String email;

        @NotBlank(message = "{auth-user.password.not-blank}")
        private String password;

        @NotBlank(message = "{auth-user.nick-name.not-blank}")
        private String nickName;

        @NotNull(message = "{auth-user.age.not-null}")
        private Long age;

        @NotNull(message = "{auth-user.gender.not-null}")
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
