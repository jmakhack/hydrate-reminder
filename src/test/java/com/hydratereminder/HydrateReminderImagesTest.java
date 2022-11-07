package com.hydratereminder;

import com.hydratereminder.images.HydrateReminderImages;
import com.hydratereminder.images.ImageCategories;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>The unit tests for the hydrate reminder images logic
 * </p>
 */
class HydrateReminderImagesTest
{

    /**
     * <p>Tests that the correct string display is generated for a
     * variety of different images
     * </p>
     * @param expectedString the expected display string
     * @param image the image to get the string value of
     */
    @ParameterizedTest
    @CsvSource({
            "Cup of Water, CUP_OF_WATER_IMAGE",
            "Cup of Tea, CUP_OF_TEA_IMAGE",
            "Beer, BEER_IMAGE",
            "Redberry Juice, REDBERRY_JUICE_IMAGE",
            "Super Restore, SUPER_RESTORE_IMAGE",
            "Holy Elixir, HOLY_ELIXIR_IMAGE"
    })
    /* default */ void testImagesToString(final String expectedString, final HydrateReminderImages image)
    {
        assertEquals(expectedString, image.toString(), "Unexpected string value received for image");
    }

    /**
     * <p>Tests that the correct ID is generated for an image
     * </p>
     * @param expectedItemID the expected Item ID of the image
     * @param image the image to get the string value of
     */
    @ParameterizedTest
    @CsvSource({
            "6953, BOTTLED_WATER_IMAGE",
            "7702, TEAPOT_IMAGE",
            "8940, RUM_IMAGE",
            "5935, COCONUT_MILK_IMAGE",
            "5984, WATERMELON_SLICE_IMAGE",
            "1773, PURPLE_DYE_IMAGE"
    })
    /* default */ void testImagesGetID(final Integer expectedItemID, final HydrateReminderImages image)
    {
        assertEquals(expectedItemID, image.getID(), "Unexpected id received for image");
    }

    /**
     * <p>Tests that the correct category is returned for a variety
     * of different images
     * </p>
     * @param category the image category
     * @param image the image to get the string value of
     */
    @ParameterizedTest
    @CsvSource({
            "WATER, CIRCLET_OF_WATER_IMAGE",
            "TEA, NETTLE_TEA_IMAGE",
            "ALCOHOL, VODKA_IMAGE",
            "DRINKCONTAINERS, ICE_COOLER_IMAGE",
            "POTIONCATEGORY, PRAYER_MIX_IMAGE",
            "OTHER, FROZEN_JUG_IMAGE"
    })
    /* default */ void testImagesGetCategory(final ImageCategories category, final HydrateReminderImages image)
    {
        assertEquals(category, image.getCategory(), "Unexpected category received for image");
    }
}
