package com.kmhoon.service.service.refrigerator;

import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.model.entity.Refrigerator;
import com.kmhoon.common.model.entity.RefrigeratorItem;
import com.kmhoon.common.repository.RefrigeratorItemRepository;
import com.kmhoon.common.repository.RefrigeratorRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.refrigerator.RefrigeratorExceptionCode;
import com.kmhoon.service.service.pet.PetCommonService;
import com.kmhoon.service.service.refrigerator.response.RefrigeratorServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefrigeratorService {

    private final PetCommonService petCommonService;
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefrigeratorItemRepository refrigeratorItemRepository;

    @Transactional(readOnly = true)
    public RefrigeratorServiceResponse.GetRefrigeratorDetail getRefrigeratorDetail(final Long petId) {
        Pet pet = petCommonService.getPetBy(petId);
        Refrigerator refrigerator = refrigeratorRepository.findByPet(pet).orElseThrow(() -> new DiaryServiceException(RefrigeratorExceptionCode.REFRIGERATOR_NOT_FOUND));
        return RefrigeratorServiceResponse.GetRefrigeratorDetail.of(refrigerator);
    }

    @Transactional
    public void addRefrigeratorItem(final Long petId, RefrigeratorServiceResponse.RefrigeratorItemDto request) {
        Pet pet = petCommonService.getPetBy(petId);
        Refrigerator refrigerator = pet.getRefrigerator();
        RefrigeratorItem refrigeratorItem = RefrigeratorItem.builder()
                .information(request.getInformation())
                .name(request.getName())
                .itemType(request.getItemType())
                .refrigerator(refrigerator)
                .build();
        refrigeratorItemRepository.save(refrigeratorItem);
    }
}
