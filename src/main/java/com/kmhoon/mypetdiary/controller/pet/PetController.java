package com.kmhoon.mypetdiary.controller.pet;

import com.kmhoon.mypetdiary.dto.pet.GetPetInfoResponse;
import com.kmhoon.mypetdiary.dto.pet.GetPetListResponse;
import com.kmhoon.mypetdiary.dto.pet.UpdatePetInfoRequest;
import com.kmhoon.mypetdiary.dto.pet.UpdatePetInfoResponse;
import com.kmhoon.mypetdiary.service.pet.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    @GetMapping("/")
    public GetPetListResponse getPetList(@RequestParam Long ownerId) {
        return petService.getPetList(ownerId);
    }

    @GetMapping("/{petId}")
    public GetPetInfoResponse getPetInfo(@PathVariable Long petId) {
        return petService.getPetInfo(petId);
    }

    @PutMapping("/{petId}")
    public UpdatePetInfoResponse updatePet(@PathVariable Long petId, @RequestBody @Valid UpdatePetInfoRequest request) {
        return petService.updatePet(petId, request);
    }

    @DeleteMapping("/{petId}")
    public void deletePet(@PathVariable Long petId){
        petService.deletePet(petId);
    }
}
