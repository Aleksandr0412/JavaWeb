package com.aleksandr0412.springdata.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    FANTASY("Фэнтези"),
    FANTASTIC("Фантастика"),
    DETECTIVE("Детектив");

    private final String genreName;

    @Override
    public String toString() {
        return genreName;
    }
}