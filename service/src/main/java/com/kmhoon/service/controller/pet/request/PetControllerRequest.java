package com.kmhoon.service.controller.pet.request;

import com.kmhoon.common.enums.PetGender;
import com.kmhoon.service.service.pet.request.PetServiceRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.kmhoon.service.messages.ValidationMessage.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PetControllerRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UpdatePet {

        @NotNull(message = INPUT_AGE_IS_NULL)
        private Long age;

        @NotBlank(message = INPUT_NAME_IS_BLANK)
        private String name;

        @NotNull(message = INPUT_GENDER_IS_NULL)
        private PetGender gender;

        @NotNull(message = INPUT_WEIGHT_IS_NULL)
        private Double weight;

        @NotBlank(message = INPUT_SPECIES_IS_BLANK)
        private String species;

        @NotBlank(message = INPUT_REGISTERED_NUMBER_IS_BLANK)
        private String registeredNumber;

        @NotNull(message = INPUT_LIVE_IS_NULL)
        private Boolean live;

        @NotNull(message = INPUT_ADOPTED_DATE_IS_NULL)
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

        @NotNull(message = INPUT_AGE_IS_NULL)
        private Long age;

        @NotBlank(message = INPUT_NAME_IS_BLANK)
        private String name;

        @NotNull(message = INPUT_GENDER_IS_NULL)
        private PetGender gender;

        @NotNull(message = INPUT_WEIGHT_IS_NULL)
        private Double weight;

        @NotBlank(message = INPUT_SPECIES_IS_BLANK)
        private String species;

        @NotBlank(message = INPUT_REGISTERED_NUMBER_IS_BLANK)
        private String registeredNumber;

        @NotNull(message = INPUT_LIVE_IS_NULL)
        private Boolean live;

        @NotNull(message = INPUT_ADOPTED_DATE_IS_NULL)
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
