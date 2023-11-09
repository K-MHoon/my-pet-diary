package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.model.entity.pet.Pet;
import com.kmhoon.common.repository.pet.PetRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.pet.PetExceptionCode;
import com.kmhoon.service.service.owner.OwnerCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetCommonService {

    private final PetRepository petRepository;
    private final OwnerCommonService ownerCommonService;

    @Transactional(readOnly = true)
    public Pet getAuthorizedPet(Long petId) {
        return petRepository.findByIdAndIsUseAndOwner(petId, IsUse.YES, ownerCommonService.getLoggedInOwner())
                .orElseThrow(() -> new DiaryServiceException(PetExceptionCode.PET_NOT_FOUND));
    }
}
