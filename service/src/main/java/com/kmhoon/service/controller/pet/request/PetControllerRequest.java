package com.kmhoon.service.controller.pet.request;

import com.kmhoon.common.enums.PetGender;
import com.kmhoon.service.service.pet.request.PetServiceRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetControllerRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UpdatePet {

        @NotNull(message = "{pet.age.not-null}")
        private Long age;

        @NotBlank(message = "{pet.name.not-blank}")
        private String name;

        @NotNull(message = "{pet.gender.not-null}")
        private PetGender gender;

        @NotNull(message = "{pet.weight.not-null}")
        private Double weight;

        @NotBlank(message = "{pet.species.not-blank}")
        private String species;

        @NotBlank(message = "{pet.registered-number.not-blank}")
        private String registeredNumber;

        @NotNull(message = "{pet.live.not-null}")
        private Boolean live;

        @NotNull(message = "{pet.adopted-date.not-null}")
        private LocalDateTime adoptedDate;

        public PetServiceRequest.UpdatePet toServiceRequest() {
            return PetServiceRequest.UpdatePet.builder()
                    .age(this.age)
                    .name(this.name)
                    .gender(this.gender)
                    .weight(this.weight)
                    .species(this.species)
                    .registeredNumber(this.registeredNumber)
                    .live(this.live)
                    .adoptedDate(this.adoptedDate)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class RegisterPet {

        @NotNull(message = "{pet.age.not-null}")
        private Long age;

        @NotBlank(message = "{pet.name.not-blank}")
        private String name;

        @NotNull(message = "{pet.gender.not-null}")
        private PetGender gender;

        @NotNull(message = "{pet.weight.not-null}")
        private Double weight;

        @NotBlank(message = "{pet.species.not-blank}")
        private String species;

        @NotBlank(message = "{pet.registered-number.not-blank}")
        private String registeredNumber;

        @NotNull(message = "{pet.live.not-null}")
        private Boolean live;

        @NotNull(message = "{pet.adopted-date.not-null}")
        private LocalDateTime adoptedDate;

        public PetServiceRequest.RegisterPet toServiceRequest() {
            return PetServiceRequest.RegisterPet.builder()
                    .age(this.age)
                    .name(this.name)
                    .gender(this.gender)
                    .weight(this.weight)
                    .species(this.species)
                    .registeredNumber(this.registeredNumber)
                    .live(this.live)
                    .adoptedDate(this.adoptedDate)
                    .build();
        }
    }
}
