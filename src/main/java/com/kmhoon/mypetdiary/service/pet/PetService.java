package com.kmhoon.mypetdiary.service.pet;

import com.kmhoon.mypetdiary.dto.pet.GetPetListResponse;
import com.kmhoon.mypetdiary.dto.pet.GetPetDto;
import com.kmhoon.mypetdiary.entity.Pet;
import com.kmhoon.mypetdiary.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @Transactional
    public GetPetListResponse getPetList(Long ownerId) {
        ModelMapper modelMapper = new ModelMapper();
        List<Pet> petList = petRepository.findAllByOwnerId(ownerId);
        List<GetPetDto> petDtoList = new ArrayList<>();

        for (Pet pet : petList) {
            petDtoList.add(modelMapper.map(pet, GetPetDto.class));
        }

       return GetPetListResponse.builder()
               .petList(petDtoList)
               .build();
    }
}
