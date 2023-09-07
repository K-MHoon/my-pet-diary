package com.kmhoon.service.service.pet.response;

import com.kmhoon.common.enums.Gender;
import com.kmhoon.common.model.entity.Pet;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetServiceResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetPetList {

        private List<GetPetDetail> petList;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetPetDetail {

        private Long id;
        private Long age;
        private String name;
        private Gender gender;
        private Double weight;
        private String species;
        private String registeredNumber;
        private Boolean live;
        private LocalDateTime adoptedDate;

        public static GetPetDetail of(Pet pet) {
            return GetPetDetail.builder()
                    .id(pet.getId())
                    .age(pet.getAge())
                    .name(pet.getName())
                    .gender(pet.getGender())
                    .weight(pet.getWeight())
                    .species(pet.getSpecies())
                    .registeredNumber(pet.getRegisteredNumber())
                    .live(pet.getLive())
                    .adoptedDate(pet.getAdoptedDate())
                    .build();
        }
    }

}
