package com.kmhoon.service.controller.user;

import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.service.service.user.LoginLogService;
import com.kmhoon.service.service.user.response.GetLoginLogListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/login/log")
public class LoginLogController {

    private final LoginLogService loginLogService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetLoginLogListResponse getLoginLogList(@RequestParam(value = "email", required = false) String email,
                                                   @RequestParam(value = "status", required = false) LoginStatus loginStatus,
                                                   @RequestParam(value = "start-date", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startDate,
                                                   @RequestParam(value = "end-date", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate endDate,
                                                   @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return loginLogService.getLoginLogList(email, loginStatus, startDate, endDate, pageable);
    }

}
