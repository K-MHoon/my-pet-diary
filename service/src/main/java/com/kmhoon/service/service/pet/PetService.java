package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.ExceptionCode;
import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.repository.PetRepository;
import com.kmhoon.service.service.pet.response.PetServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @Transactional
    public PetServiceResponse.GetPetList getPetList(Long ownerId) {
        List<Pet> petList = petRepository.findAllByOwnerId(ownerId);

        List<PetServiceResponse.GetPetDetail> petDetailList = petList.stream()
                .map(PetServiceResponse.GetPetDetail::of)
                .collect(Collectors.toList());

       return PetServiceResponse.GetPetList.builder()
               .petList(petDetailList)
               .build();
    }

    @Transactional(readOnly = true)
    public PetServiceResponse.GetPetDetail getPetDetail(Long id) {
        Pet pet = getPetBy(id);
        return PetServiceResponse.GetPetDetail.of(pet);
    }

    private Pet getPetBy(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.PET_NOT_FOUND_EXCEPTION.getValue()));
    }

    @Transactional
    public void updatePet(Long id, PetServiceRequest.UpdatePet request) {
        Pet pet = getPetBy(id);

        pet.setAge(request.getAge());
        pet.setAdoptedDate(request.getAdoptedDate());
        pet.setGender(request.getGender());
        pet.setLive(request.getLive());
        pet.setName(request.getName());
        pet.setSpecies(request.getSpecies());
        pet.setWeight(request.getWeight());
        pet.setRegisteredNumber(request.getRegisteredNumber());
    }

    @Transactional
    public void deletePet(Long petId) {
        // 펫 ID 실존 검증을 위해 ID가 아닌, 엔티티로 삭제하는 걸 선택했다.
        petRepository.delete(getPetById(petId));
    }
}
