package com.kmhoon.service.service.user;

import com.kmhoon.common.enums.UserGender;
import com.kmhoon.common.model.entity.AuthUser;
import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.repository.auth.AuthUserRepository;
import com.kmhoon.common.repository.owner.OwnerRepository;
import com.kmhoon.service.service.ServiceIntegrationTestBase;
import com.kmhoon.service.service.user.request.AuthUserServiceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthUserServiceTest extends ServiceIntegrationTestBase {

    @Autowired
    AuthUserService authUserService;

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입에 성공하면 AuthUser와 Owner가 생성된다.")
    void register() {
        // given
        AuthUserServiceRequest.Register request = AuthUserServiceRequest.Register.builder()
                .email("testUser@test.com")
                .password("testPassword")
                .age(10L)
                .gender(UserGender.MAN)
                .nickName("testUserName")
                .build();

        // when
        authUserService.register(request);

        // then
        AuthUser authUser = authUserRepository.findById("testUser@test.com").get();
        Owner owner = ownerRepository.findByEmail("testUser@test.com").get();

        assertThat(authUser.getEmail()).isEqualTo("testUser@test.com");
        assertThat(owner).extracting("nickName", "gender", "email")
                .contains("testUserName", UserGender.MAN, "testUser@test.com");
    }

    @Test
    @DisplayName("중복된 이메일은 존재할 수 없다.")
    void registerDuplicatedEmail() {
        // given
        AuthUser authUser = AuthUser.builder()
                .email("testUser@test.com")
                .password("testUserPassword")
                .build();
        authUserRepository.save(authUser);

        AuthUserServiceRequest.Register request = AuthUserServiceRequest.Register.builder()
                .email("testUser@test.com")
                .password("testPassword")
                .age(10L)
                .gender(UserGender.MAN)
                .nickName("testUserName")
                .build();

        // when & then
        assertThatThrownBy(() -> authUserService.register(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 이메일 입니다.");
    }
}