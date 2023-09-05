package com.kmhoon.mypetdiary.service.pet;

import com.kmhoon.mypetdiary.dto.pet.*;
import com.kmhoon.mypetdiary.entity.Pet;
import com.kmhoon.mypetdiary.enums.ExceptionCode;
import com.kmhoon.mypetdiary.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public GetPetListResponse getPetList(Long ownerId) {
        List<Pet> petList = petRepository.findAllByOwnerId(ownerId);
        List<GetPetDto> petDtoList = new ArrayList<>();

        for (Pet pet : petList) {
            petDtoList.add(modelMapper.map(pet, GetPetDto.class));
        }

       return GetPetListResponse.builder()
               .petList(petDtoList)
               .build();
    }

    @Transactional(readOnly = true)
    public PetServiceResponse.GetPetInfo getPetInfo(Long petId) {
        Pet pet = getPetBy(petId);
        return PetServiceResponse.GetPetInfo.of(pet);
    }

    private Pet getPetBy(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.PET_NOT_FOUND_EXCEPTION.getValue()));
        return pet;
    }

    @Transactional
    public UpdatePetInfoResponse updatePet(Long petId, UpdatePetInfoRequest request) {
        Pet pet = getPetById(petId);

        pet.setAge(request.getAge());
        pet.setAdoptedDate(request.getAdoptedDate());
        pet.setGender(request.getGender());
        pet.setLive(request.getLive());
        pet.setName(request.getName());
        pet.setSpecies(request.getSpecies());
        pet.setWeight(request.getWeight());
        pet.setRegisteredNumber(request.getRegisteredNumber());

        UpdatePetInfoResponse response = new UpdatePetInfoResponse();
        response.setResult(true);
        return response;
    }

    @Transactional
    public void deletePet(Long petId) {
        // 펫 ID 실존 검증을 위해 ID가 아닌, 엔티티로 삭제하는 걸 선택했다.
        petRepository.delete(getPetById(petId));
    }
}
