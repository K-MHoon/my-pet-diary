package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.enums.PetGender;
import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.repository.pet.PetRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.pet.PetExceptionCode;
import com.kmhoon.service.service.ServiceIntegrationTestBase;
import com.kmhoon.service.service.pet.request.PetServiceRequest;
import com.kmhoon.service.service.pet.response.PetServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PetServiceTest extends ServiceIntegrationTestBase {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PetService service;
    @Test
    @DisplayName("반려동물 목록을 확인한다.")
    void getPetList() {
        // given
        Owner owner = ownerHelper.createSimpleOwner();

        Pet pet1 = petHelper.createPetBuilder(owner).build();
        Pet pet2 = petHelper.createPetBuilder(owner)
                .name("테스트펫2")
                .gender(PetGender.FEMALE)
                .weight(6.2)
                .registeredNumber("testRegisteredNumber2")
                .adoptedDate(LocalDateTime.of(2023, 9, 10, 0, 0, 0))
                .owner(owner)
                .build();

        petRepository.save(pet1);
        petRepository.save(pet2);

        // when
        PetServiceResponse.GetPetList result = service.getPetList(owner.getId());

        // then
        assertThat(result.getPetList()).hasSize(2)
                .extracting("name", "weight", "species", "registeredNumber")
                .contains(tuple("테스트펫1", 5.1, "시바견", "testRegisteredNumber"),
                        tuple("테스트펫2", 6.2, "시바견", "testRegisteredNumber2"));
    }


    @Test
    @DisplayName("펫 상세 정보를 조회한다.")
    void getPetDetail() {
        // given
        Owner owner = ownerHelper.createSimpleOwner();
        Pet pet = petHelper.createSimplePet(owner);

        // when
        PetServiceResponse.GetPetDetail petDetail = service.getPetDetail(pet.getId());

        // then
        assertThat(petDetail).extracting("id",
                        "age",
                        "name",
                        "gender",
                        "weight",
                        "species",
                        "registeredNumber",
                        "live",
                        "adoptedDate")
                .contains(pet.getId(),
                        pet.getAge(),
                        pet.getName(),
                        pet.getGender(),
                        pet.getWeight(),
                        pet.getSpecies(),
                        pet.getRegisteredNumber(),
                        pet.getLive(),
                        pet.getAdoptedDate());
    }

    @Test
    @DisplayName("저장된 Pet Id만 조회할 수 있다.")
    void getPetDetailPetId() {
        assertThatThrownBy(() -> service.getPetDetail(Long.MAX_VALUE))
                .isInstanceOf(DiaryServiceException.class)
                .hasMessage(PetExceptionCode.PET_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("펫 정보를 업데이트 한다.")
    void updatePet() {
        // given
        Owner owner = ownerHelper.createSimpleOwner();
        Pet pet = petHelper.createSimplePet(owner);

        PetServiceRequest.UpdatePet request = PetServiceRequest.UpdatePet.builder()
                .age(10L)
                .adoptedDate(LocalDateTime.of(2023, 10, 7, 0, 0, 0))
                .gender(PetGender.FEMALE)
                .live(Boolean.FALSE)
                .name("업데이트펫")
                .species("시츄")
                .weight(7.1d)
                .registeredNumber("업데이트RegisteredNumber")
                .build();

        // when
        service.updatePet(pet.getId(), request);

        // then
        Pet result = petRepository.findById(pet.getId()).get();
        assertThat(result)
                .extracting("id",
                        "age",
                        "name",
                        "gender",
                        "weight",
                        "species",
                        "registeredNumber",
                        "live",
                        "adoptedDate",
                        "owner")
                .contains(pet.getId(),
                        request.getAge(),
                        request.getName(),
                        request.getGender(),
                        request.getWeight(),
                        request.getSpecies(),
                        request.getRegisteredNumber(),
                        request.getLive(),
                        request.getAdoptedDate(),
                        owner);
    }

    @Test
    @DisplayName("펫을 삭제한다.")
    void deletePet() {
        // given
        Owner owner = ownerHelper.createSimpleOwner();
        Pet pet = petHelper.createSimplePet(owner);

        // when
        service.deletePet(pet.getId());

        // then
        Pet result = petRepository.findById(pet.getId()).get();
        assertThat(result.getIsUse()).isEqualTo(IsUse.NO);
    }
}