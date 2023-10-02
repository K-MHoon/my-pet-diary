package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.PetGender;
import com.kmhoon.common.enums.UserGender;
import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.repository.OwnerRepository;
import com.kmhoon.common.repository.PetRepository;
import com.kmhoon.service.service.ServiceIntegrationTestBase;
import com.kmhoon.service.service.pet.response.PetServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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
        Owner owner = Owner.builder()
                .nickName("testUser")
                .gender(UserGender.MAN)
                .email("test@test.com")
                .build();
        Owner savedOwner = ownerRepository.save(owner);

        Pet pet1 = Pet.builder()
                .age(1L)
                .name("테스트펫1")
                .gender(PetGender.MALE)
                .weight(5.1)
                .species("시바견")
                .registeredNumber("testRegisteredNumber")
                .live(Boolean.TRUE)
                .adoptedDate(LocalDateTime.of(2023, 9, 11, 0, 0, 0))
                .owner(savedOwner)
                .build();

        Pet pet2 = Pet.builder()
                .age(1L)
                .name("테스트펫2")
                .gender(PetGender.FEMALE)
                .weight(6.2)
                .species("시바견")
                .registeredNumber("testRegisteredNumber2")
                .live(Boolean.TRUE)
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
}