package com.kmhoon.common.repository.log;

import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.log.LoginLog;

import java.time.LocalDate;
import java.util.List;

public interface LoginLogRepositorySupport {

    List<LoginLog> findAllByEmailAndLoginStatusAndDateBetween(String email, LoginStatus loginStatus, LocalDate startDate, LocalDate endDate);
}
