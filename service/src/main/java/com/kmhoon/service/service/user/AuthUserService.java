package com.kmhoon.service.service.user;

import com.kmhoon.common.model.entity.auth.AuthRole;
import com.kmhoon.common.model.entity.auth.AuthUser;
import com.kmhoon.common.model.entity.auth.AuthUserRoleMap;
import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.repository.auth.AuthRoleRepository;
import com.kmhoon.common.repository.auth.AuthUserRepository;
import com.kmhoon.common.repository.auth.AuthUserRoleMapRepository;
import com.kmhoon.common.repository.owner.OwnerRepository;
import com.kmhoon.service.service.user.request.AuthUserServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthUserService {

    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;
    private final AuthUserRoleMapRepository authUserRoleMapRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public void register(AuthUserServiceRequest.Register request) {

        if(authUserRepository.existsById(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        AuthUser authUser = createAuthUserBy(request);
        AuthRole authRole = getAuthRole();
        createAuthRoleMap(authUser, authRole);

        createOwner(request);
    }

    private void createOwner(AuthUserServiceRequest.Register request) {
        Owner owner = Owner.builder()
                .email(request.getEmail())
                .nickName(request.getNickName())
                .gender(request.getGender())
                .build();

        ownerRepository.save(owner);
    }

    private void createAuthRoleMap(AuthUser authUser, AuthRole authRole) {
        AuthUserRoleMap authUserRoleMap = AuthUserRoleMap.builder()
                .user(authUser)
                .role(authRole)
                .build();

        authUserRoleMapRepository.save(authUserRoleMap);
    }

    private AuthRole getAuthRole() {
        return authRoleRepository.findById("USER").orElseThrow(() -> new EntityNotFoundException("사용자 역할을 찾을 수 없습니다."));
    }

    private AuthUser createAuthUserBy(AuthUserServiceRequest.Register request) {
        AuthUser authUser = AuthUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return authUserRepository.save(authUser);
    }
}
