package com.kmhoon.service.service.user;

import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.log.LoginLog;
import com.kmhoon.common.repository.log.LoginLogRepository;
import com.kmhoon.service.service.user.response.GetLoginLogListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;

    @Transactional(readOnly = true)
    public GetLoginLogListResponse getLoginLogList(String email, LoginStatus loginStatus, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        List<LoginLog> loginLogList = loginLogRepository.findAllByEmailAndLoginStatusAndDateBetween(email, loginStatus, startDate, endDate);
        List<GetLoginLogListResponse.LoginLogDto> loginLogDtoList = loginLogList.stream()
                .map(GetLoginLogListResponse.LoginLogDto::of)
                .sorted(byCreatedAtDesc())
                .collect(Collectors.toList());
        return new GetLoginLogListResponse(loginLogDtoList);
    }

    private Comparator<GetLoginLogListResponse.LoginLogDto> byCreatedAtDesc() {
        return Comparator.comparing(GetLoginLogListResponse.LoginLogDto::getCreatedAt).reversed();
    }
}
