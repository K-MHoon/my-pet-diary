package com.kmhoon.mypetdiary.dto.pet;

import com.kmhoon.mypetdiary.enums.Gender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatePetInfoRequest {

    private Long age;
    private String name;
    private Gender gender;
    private Double weight;
    private String species;
    private String registeredNumber;
    private Boolean live;
    private LocalDateTime adoptedDate;
}
