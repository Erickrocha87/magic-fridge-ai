package com.rocha.MagicFridgeAi.entity;

import lombok.Getter;

@Getter
public enum Category {
    MEATS("meats"),
    VEGETABLES("vegetables"),
    FRUITS("fruits"),
    SEAFOOD("seafood"),
    CEREALS("cereals"),
    DAIRY("dairy"),
    SNACKS("snacks"),
    SWEETS("sweets"),
    POULTRY(("poultry")),
    GRAINS("grains"),;

    private final String category;

    Category(String category) {
        this.category = category;
    }
}
