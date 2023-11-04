package com.kmhoon.common.repository.log;

import com.kmhoon.common.model.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long>, LoginLogRepositorySupport {
}
