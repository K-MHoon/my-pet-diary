package com.kmhoon.service.controller.pet;

import com.kmhoon.service.controller.request.PetControllerRequest;
import com.kmhoon.service.service.pet.PetService;
import com.kmhoon.service.service.pet.response.PetServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PetServiceResponse.GetPetList getPetList(@RequestParam Long ownerId) {
        return petService.getPetList(ownerId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetServiceResponse.GetPetDetail getPetDetail(@PathVariable Long id) {
        return petService.getPetDetail(id);
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
