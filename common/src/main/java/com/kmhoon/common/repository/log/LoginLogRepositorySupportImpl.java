package com.kmhoon.common.repository.log;

import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.log.LoginLog;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static com.kmhoon.common.model.entity.log.QLoginLog.loginLog;

public class LoginLogRepositorySupportImpl extends QuerydslRepositorySupport implements LoginLogRepositorySupport {

    public LoginLogRepositorySupportImpl() {
        super(LoginLog.class);
    }

    @Override
    public List<LoginLog> findAllByEmailAndLoginStatusAndDateBetween(String email, LoginStatus loginStatus, LocalDate startDate, LocalDate endDate) {
        return from(loginLog)
                .where(searchByEmail(email),
                        searchByLoginStatus(loginStatus),
                        searchDateBetweenBy(startDate, endDate))
                .fetch();
    }

    private BooleanExpression searchDateBetweenBy(LocalDate startDate, LocalDate endDate) {
        if(Objects.isNull(startDate) || Objects.isNull(endDate)) return null;
        return loginLog.createdAt.between(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
    }

    private BooleanExpression searchByLoginStatus(LoginStatus loginStatus) {
        if(Objects.isNull(loginStatus)) return null;
        return loginLog.loginStatus.eq(loginStatus);
    }

    private BooleanExpression searchByEmail(String email) {
        if(!StringUtils.hasText(email)) return null;
        return loginLog.loginEmail.eq(email);
    }
}
