package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.repository.PetRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.pet.PetExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetCommonService {

    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public Pet getPetBy(Long petId) {
        return petRepository.findByIdAndIsUse(petId, IsUse.YES)
                .orElseThrow(() -> new DiaryServiceException(PetExceptionCode.PET_NOT_FOUND));
    }
}
