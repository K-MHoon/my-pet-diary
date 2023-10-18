package com.kmhoon.service.service.pet;

import com.kmhoon.common.enums.IsUse;
import com.kmhoon.common.model.entity.Owner;
import com.kmhoon.common.model.entity.Pet;
import com.kmhoon.common.model.entity.Refrigerator;
import com.kmhoon.common.repository.OwnerRepository;
import com.kmhoon.common.repository.PetRepository;
import com.kmhoon.common.repository.RefrigeratorRepository;
import com.kmhoon.service.exception.DiaryServiceException;
import com.kmhoon.service.exception.enums.entity.user.UserExceptionCode;
import com.kmhoon.service.service.pet.request.PetServiceRequest;
import com.kmhoon.service.service.pet.response.PetServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final PetCommonService petCommonService;

    @Transactional(readOnly = true)
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
        Pet pet = petCommonService.getPetBy(id);
        return PetServiceResponse.GetPetDetail.of(pet);
    }

    private Owner getLoggedInUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return ownerRepository.findByEmail(userEmail).orElseThrow(() -> new DiaryServiceException(UserExceptionCode.USER_EMAIL_NOT_FOUND));
    }

    @Transactional
    public void registerPet(PetServiceRequest.RegisterPet request) {
        Pet savedPet = createPetBy(request);
        createRefrigeratorBy(savedPet);
    }

    private Refrigerator createRefrigeratorBy(Pet pet) {
        Refrigerator refrigerator = Refrigerator.builder()
                .pet(pet)
                .build();
        return refrigeratorRepository.save(refrigerator);
    }

    private Pet createPetBy(PetServiceRequest.RegisterPet request) {
        Owner loggedInUser = getLoggedInUser();
        Pet pet = Pet.builder()
                .age(request.getAge())
                .name(request.getName())
                .gender(request.getGender())
                .weight(request.getWeight())
                .species(request.getSpecies())
                .registeredNumber(request.getRegisteredNumber())
                .live(request.getLive())
                .isUse(IsUse.YES)
                .adoptedDate(request.getAdoptedDate())
                .owner(loggedInUser)
                .build();
        return petRepository.save(pet);
    }

    @Transactional
    public void updatePet(Long id, PetServiceRequest.UpdatePet request) {
        Pet pet = petCommonService.getPetBy(id);

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
    public void deletePet(Long id) {
        Pet pet = petCommonService.getPetBy(id);
        pet.updateIsUse(IsUse.NO);
    }
}
