package com.kmhoon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemType {

    FEED("FEED"), SNACK("SNACK"), DRINK("DRINK"), DRUG("DRUG");

    private final String value;
}
