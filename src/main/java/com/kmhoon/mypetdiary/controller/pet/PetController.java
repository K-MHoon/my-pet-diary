package com.kmhoon.mypetdiary.controller.pet;

import com.kmhoon.mypetdiary.dto.pet.GetPetListResponse;
import com.kmhoon.mypetdiary.service.pet.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
