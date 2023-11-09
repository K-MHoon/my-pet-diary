package com.kmhoon.service.service.refrigerator;

import com.kmhoon.common.model.entity.pet.Pet;
import com.kmhoon.common.model.entity.refrigerator.Refrigerator;
import com.kmhoon.common.model.entity.refrigerator.RefrigeratorItem;
import com.kmhoon.common.repository.refrigerator.RefrigeratorItemRepository;
import com.kmhoon.common.repository.refrigerator.RefrigeratorRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.refrigerator.RefrigeratorExceptionCode;
import com.kmhoon.service.exception.enums.entity.refrigerator.RefrigeratorItemExceptionCode;
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
        Pet pet = petCommonService.getAuthorizedPet(petId);
        Refrigerator refrigerator = refrigeratorRepository.findByPet(pet).orElseThrow(() -> new DiaryServiceException(RefrigeratorExceptionCode.REFRIGERATOR_NOT_FOUND));
        return RefrigeratorServiceResponse.GetRefrigeratorDetail.of(refrigerator);
    }

    @Transactional
    public void addRefrigeratorItem(final Long petId, RefrigeratorServiceResponse.RefrigeratorItemDto request) {
        Pet pet = petCommonService.getAuthorizedPet(petId);
        Refrigerator refrigerator = pet.getRefrigerator();
        RefrigeratorItem refrigeratorItem = RefrigeratorItem.builder()
                .information(request.getInformation())
                .name(request.getName())
                .itemType(request.getItemType())
                .refrigerator(refrigerator)
                .build();
        refrigeratorItemRepository.save(refrigeratorItem);
    }

    @Transactional
    public void removeRefrigeratorItem(final Long petId, final Long refrigeratorItemId) {
        Pet pet = petCommonService.getAuthorizedPet(petId);
        Refrigerator refrigerator = refrigeratorRepository.findByPet(pet).orElseThrow(() -> new DiaryServiceException(RefrigeratorExceptionCode.REFRIGERATOR_NOT_FOUND));
        RefrigeratorItem refrigeratorItem = refrigerator.getRefrigeratorItemList().stream()
                .filter(r -> r.getId() == refrigeratorItemId)
                .findAny()
                .orElseThrow(() -> new DiaryServiceException(RefrigeratorItemExceptionCode.REFRIGERATOR_ITEM_NOT_FOUND));
        refrigeratorItemRepository.delete(refrigeratorItem);
    }
}
