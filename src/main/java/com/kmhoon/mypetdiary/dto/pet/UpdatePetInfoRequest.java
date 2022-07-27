package com.kmhoon.mypetdiary.dto.pet;

import com.kmhoon.mypetdiary.messages.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static com.kmhoon.mypetdiary.messages.ValidationMessage.*;

/**
 * 반려동물 정보를 업데이트 한다.
 *
 * 수정되지 않는 값은 Default 값 그대로 입력 받는다.
 */
@Data
public class UpdatePetInfoRequest {

    @NotNull(message = INPUT_AGE_IS_NULL)
    private Long age;

    @NotBlank(message = INPUT_NAME_IS_BLANK)
    private String name;

    @NotBlank(message = INPUT_GENDER_IS_BLANK)
    private String gender;

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
}
