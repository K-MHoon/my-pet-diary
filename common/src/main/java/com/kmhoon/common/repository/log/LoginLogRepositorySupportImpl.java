package com.kmhoon.common.repository.log;

import com.kmhoon.common.model.entity.LoginLog;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class LoginLogRepositorySupportImpl extends QuerydslRepositorySupport implements LoginLogRepositorySupport {

    public LoginLogRepositorySupportImpl() {
        super(LoginLog.class);
    }
}
