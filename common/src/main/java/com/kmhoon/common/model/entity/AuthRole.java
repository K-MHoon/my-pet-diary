package com.kmhoon.common.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "tb_auth_role")
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthRole {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String role;

    @OneToMany(mappedBy = "role")
    @Builder.Default
    private List<AuthUserRoleMap> roleList = new ArrayList<>();
}
