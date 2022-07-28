package com.kmhoon.mypetdiary.service.pet;

import com.kmhoon.mypetdiary.dto.pet.GetPetInfoResponse;
import com.kmhoon.mypetdiary.dto.pet.GetPetListResponse;
import com.kmhoon.mypetdiary.entity.Owner;
import com.kmhoon.mypetdiary.entity.Pet;
import com.kmhoon.mypetdiary.enums.ExceptionCode;
import com.kmhoon.mypetdiary.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private ModelMapper modelMapper;

    // Entities
    private Owner owner;
    private Pet pet1;
    private Pet pet2;
    private Pet pet3;

    private static final Long FAKE_OWNER_ID = 1L;
    private static final Long FAKE_PET1_ID = 1L;

    @BeforeEach
    void init() {
        owner = new Owner();
        pet1 = new Pet();
        pet2 = new Pet();
        pet3 = new Pet();

        owner.addPet(pet1);
        owner.addPet(pet2);
        owner.addPet(pet3);

        ReflectionTestUtils.setField(owner, "id", FAKE_OWNER_ID);
        ReflectionTestUtils.setField(pet1, "id", FAKE_PET1_ID);
    }

    @Test
    @DisplayName("사용자에게 해당하는 반려동물 목록이 정상적으로 출력되는지 확인한다.")
    void checkGetPetListByOwnerId() {
        when(petRepository.findAllByOwnerId(any()))
                .thenReturn(owner.getPets());

        GetPetListResponse petList = petService.getPetList(owner.getId());

        assertEquals(petList.getPetList().size(), owner.getPets().size());
    }

    @Test
    @DisplayName("잘못된 petId가 입력되는 경우, Exception을 발생시킨다.")
    void showExceptionWhenNotExistPetId() {
        when(petRepository.findById(any()))
                .thenThrow(new EntityNotFoundException(ExceptionCode.PET_NOT_FOUND_EXCEPTION.getValue()));

        assertThrows(EntityNotFoundException.class,
                () -> petService.getPetById(pet1.getId()));
    }

    @Test
    @DisplayName("정확한 petId가 입력되는 경우, 해당하는 Pet 정보를 가지고 온다.")
    void getExistedPetWhenInputNormalPetId() {
        when(petRepository.findById(any()))
                .thenReturn(Optional.ofNullable(pet1));

        Pet result = petService.getPetById(pet1.getId());

        assertEquals(result.getId(), pet1.getId());
    }


}