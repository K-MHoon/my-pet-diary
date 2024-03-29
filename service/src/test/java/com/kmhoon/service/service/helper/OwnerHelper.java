package com.kmhoon.service.service.helper;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.enums.UserGender;
import com.kmhoon.common.model.entity.owner.Owner;
import com.kmhoon.common.repository.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerHelper {

    private final OwnerRepository ownerRepository;

    public Owner.OwnerBuilder createOwnerBuilder() {
        return Owner.builder()
                .nickName("testUser")
                .gender(UserGender.MAN)
                .isUse(IsUse.YES)
                .email("test@test.com");
    }

    public Owner createSimpleOwner() {
        Owner owner = createOwnerBuilder().build();
        return ownerRepository.save(owner);
    }
}
