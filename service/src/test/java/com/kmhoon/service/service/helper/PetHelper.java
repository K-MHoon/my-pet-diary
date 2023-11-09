package com.kmhoon.service.service.helper;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.enums.PetGender;
import com.kmhoon.common.model.entity.owner.Owner;
import com.kmhoon.common.model.entity.pet.Pet;
import com.kmhoon.common.repository.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PetHelper {

    private final PetRepository petRepository;

    public Pet.PetBuilder createPetBuilder(Owner savedOwner) {
        return Pet.builder()
                .age(1L)
                .name("테스트펫1")
                .gender(PetGender.MALE)
                .weight(5.1)
                .species("시바견")
                .registeredNumber("testRegisteredNumber")
                .live(Boolean.TRUE)
                .isUse(IsUse.YES)
                .adoptedDate(LocalDateTime.of(2023, 9, 11, 0, 0, 0))
                .owner(savedOwner);
    }

    public Pet createSimplePet(Owner savedOwner) {
        Pet pet = createPetBuilder(savedOwner).build();
        return petRepository.save(pet);
    }
}
