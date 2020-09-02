package com.aleksandr0412.springdata.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
//@NoArgsConstructor
public enum Genre {
    FANTASY("Фэнтези"),
    FANTASTIC("Фантастика"),
    DETECTIVE("Детектив");

    private final String genreName;
}