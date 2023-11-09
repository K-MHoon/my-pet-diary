package com.kmhoon.common.repository.auth;

import com.kmhoon.common.model.entity.auth.AuthUserRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRoleMapRepository extends JpaRepository<AuthUserRoleMap, Long> {
}
