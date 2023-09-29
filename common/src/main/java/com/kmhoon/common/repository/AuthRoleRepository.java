package com.kmhoon.common.repository;

import com.kmhoon.common.model.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, String> {
}
