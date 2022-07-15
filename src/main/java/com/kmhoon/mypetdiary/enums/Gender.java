package com.kmhoon.mypetdiary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    WOMAN("WOMAN"), MAN("MAN"), MALE("MALE"), FEMALE("FEMALE");

    private String value;
}
