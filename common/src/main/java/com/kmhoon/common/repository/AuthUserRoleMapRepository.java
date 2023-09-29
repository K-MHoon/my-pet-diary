package com.kmhoon.common.repository;

import com.kmhoon.common.model.entity.AuthUserRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRoleMapRepository extends JpaRepository<AuthUserRoleMap, Long> {
}
