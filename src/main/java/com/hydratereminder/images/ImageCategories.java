package com.hydratereminder.images;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Different categories for hydrate reminder images</p>
 */
@Getter
@AllArgsConstructor
public enum ImageCategories {
    WATER("Water"),
    TEA("Tea"),
    ALCOHOL("Alcohol"),
    DRINK("Drink"),
    DRINKCONTAINERS("Container"),
    FOOD("Food"),
    POTIONCATEGORY("Potion"),
    DYE("Dye"),
    CHEMICAL("Chemical"),
    OTHER("Other");

    /**
     * Category of the image
     */
    private final String imageCategory;

    /**
     * <p>Get the category of the image as a String
     * </p>
     * @return image category
     */
    @Override
    public String toString() { return getImageCategory(); }
}
