package com.kmhoon.common.repository;

import com.kmhoon.common.model.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
}
