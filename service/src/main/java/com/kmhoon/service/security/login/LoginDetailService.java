package com.kmhoon.service.security.login;

import com.kmhoon.common.model.entity.AuthUser;
import com.kmhoon.common.repository.auth.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LoginDetailService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.authUserRepository.findById(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(AuthUser authUser) {
        return User.builder()
                .username(authUser.getEmail())
                .password(authUser.getPassword())
                .roles(authUser.getRoleList().stream()
                        .map(authUserRoleMap -> authUserRoleMap.getRole())
                        .map(authRole -> authRole.getRole())
                        .toArray(size -> new String[size]))
                .build();
    }
}
