package com.kmhoon.common.model.entity.auth;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "tb_auth_user")
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUser {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<AuthUserRoleMap> roleList = new ArrayList<>();

    private LocalDateTime lastLoggedInDate;

    private Long loginTryCount = 0l;
}
