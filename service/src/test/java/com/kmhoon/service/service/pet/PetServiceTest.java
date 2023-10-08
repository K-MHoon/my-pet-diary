package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.PetGender;
import com.kmhoon.common.enums.UserGender;
import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.repository.OwnerRepository;
import com.kmhoon.common.repository.PetRepository;
import com.kmhoon.service.exception.enums.pet.PetExceptionCode;
import com.kmhoon.service.service.ServiceIntegrationTestBase;
import com.kmhoon.service.service.pet.request.PetServiceRequest;
import com.kmhoon.service.service.pet.response.PetServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class PetServiceTest extends ServiceIntegrationTestBase {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    PetService petService;
    @Test
    @DisplayName("반려동물 목록을 확인한다.")
    void getPetList() {
        // given
        Owner owner = createOwnerBuilder().build();
        Owner savedOwner = ownerRepository.save(owner);

        Pet pet1 = createPetBuilder(savedOwner).build();
        Pet pet2 = createPetBuilder(savedOwner)
                .name("테스트펫2")
                .gender(PetGender.FEMALE)
                .weight(6.2)
                .registeredNumber("testRegisteredNumber2")
                .adoptedDate(LocalDateTime.of(2023, 9, 10, 0, 0, 0))
                .owner(savedOwner)
                .build();

        petRepository.save(pet1);
        petRepository.save(pet2);

        // when
        PetServiceResponse.GetPetList result = petService.getPetList(savedOwner.getId());

        // then
        assertThat(result.getPetList()).hasSize(2)
                .extracting("name", "weight", "species", "registeredNumber")
                .contains(tuple("테스트펫1", 5.1, "시바견", "testRegisteredNumber"),
                        tuple("테스트펫2", 6.2, "시바견", "testRegisteredNumber2"));
    }

    private Owner.OwnerBuilder createOwnerBuilder() {
        return Owner.builder()
                .nickName("testUser")
                .gender(UserGender.MAN)
                .email("test@test.com");
    }

    private Pet.PetBuilder createPetBuilder(Owner savedOwner) {
        return Pet.builder()
                .age(1L)
                .name("테스트펫1")
                .gender(PetGender.MALE)
                .weight(5.1)
                .species("시바견")
                .registeredNumber("testRegisteredNumber")
                .live(Boolean.TRUE)
                .adoptedDate(LocalDateTime.of(2023, 9, 11, 0, 0, 0))
                .owner(savedOwner);
    }

    @Test
    @DisplayName("펫 상세 정보를 조회한다.")
    void getPetDetail() {
        // given
        Owner owner = createOwnerBuilder().build();
        Owner savedOwner = ownerRepository.save(owner);

        Pet pet = createPetBuilder(savedOwner).build();
        Pet savedPet = petRepository.save(pet);

        // when
        PetServiceResponse.GetPetDetail petDetail = petService.getPetDetail(savedPet.getId());

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
                .contains(savedPet.getId(),
                        savedPet.getAge(),
                        savedPet.getName(),
                        savedPet.getGender(),
                        savedPet.getWeight(),
                        savedPet.getSpecies(),
                        savedPet.getRegisteredNumber(),
                        savedPet.getLive(),
                        savedPet.getAdoptedDate());
    }

    @Test
    @DisplayName("저장된 Pet Id만 조회할 수 있다.")
    void getPetDetailPetId() {
        assertThatThrownBy(() -> petService.getPetDetail(Long.MAX_VALUE))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(PetExceptionCode.PET_NOT_FOUND_EXCEPTION.getMessage());
    }

    @Test
    @DisplayName("펫 정보를 업데이트 한다.")
    void updatePet() {
        // given
        Owner owner = createOwnerBuilder().build();
        Owner savedOwner = ownerRepository.save(owner);

        Pet pet = createPetBuilder(savedOwner).build();
        Pet savedPet = petRepository.save(pet);

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
        petService.updatePet(savedPet.getId(), request);

        // then
        Pet result = petRepository.findById(savedPet.getId()).get();
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
                .contains(savedPet.getId(),
                        request.getAge(),
                        request.getName(),
                        request.getGender(),
                        request.getWeight(),
                        request.getSpecies(),
                        request.getRegisteredNumber(),
                        request.getLive(),
                        request.getAdoptedDate(),
                        savedOwner);
    }

    @Test
    @DisplayName("펫을 삭제한다.")
    void deletePet() {
        // given
        Owner owner = createOwnerBuilder().build();
        Owner savedOwner = ownerRepository.save(owner);

        Pet pet = createPetBuilder(savedOwner).build();
        Pet savedPet = petRepository.save(pet);

        // when
        petService.deletePet(savedPet.getId());

        // then
        Optional<Pet> result = petRepository.findById(savedPet.getId());
        assertThat(result).isEmpty();
    }
}