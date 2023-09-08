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
    public PetServiceResponse.GetPetList getPetList(@RequestParam(name = "ownerId", required = false) Long ownerId) {
        return petService.getPetList(ownerId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetServiceResponse.GetPetDetail getPetDetail(@PathVariable Long id) {
        return petService.getPetDetail(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePet(@PathVariable Long id,
                          @RequestBody @Valid PetControllerRequest.UpdatePet request) {
        petService.updatePet(id, request.toServiceRequest());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePet(@PathVariable(name = "id") Long id){
        petService.deletePet(id);
    }
}
