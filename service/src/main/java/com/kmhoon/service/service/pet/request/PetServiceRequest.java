package com.kmhoon.service.service.pet.request;

import com.kmhoon.common.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PetServiceRequest {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @Builder
    public static final class UpdatePet {

        private Long age;
        private String name;
        private Gender gender;
        private Double weight;
        private String species;
        private String registeredNumber;
        private Boolean live;
        private LocalDateTime adoptedDate;
    }

}
