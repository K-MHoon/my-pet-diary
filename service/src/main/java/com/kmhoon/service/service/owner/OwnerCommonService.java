package com.kmhoon.service.service.owner;

import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.repository.OwnerRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.user.UserExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerCommonService {

    private final OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public Owner getLoggedInOwner() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return ownerRepository.findByEmail(userEmail).orElseThrow(() -> new DiaryServiceException(UserExceptionCode.USER_EMAIL_NOT_FOUND));
    }
}
