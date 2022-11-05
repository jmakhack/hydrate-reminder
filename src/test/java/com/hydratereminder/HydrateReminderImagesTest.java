package com.hydratereminder;

import com.hydratereminder.images.HydrateReminderImages;
import com.hydratereminder.images.ImageCategories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import static net.runelite.api.ItemID.BOTTLED_WATER;
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
    void testImagesToString(final String expectedString, final HydrateReminderImages image)
    {
        assertEquals(expectedString, image.toString(), "Unexpected string value received for image");
    }

    /**
     * <p>Tests that the correct ID is generated for an image
     * </p>
     */
    @Test
    void testImagesGetID()
    {
        assertEquals(BOTTLED_WATER, HydrateReminderImages.BOTTLED_WATER_IMAGE.getID(),
                "Unexpected id received for image");
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
    void testImagesGetCategory(final ImageCategories category, final HydrateReminderImages image)
    {
        assertEquals(category, image.getCategory(), "Unexpected category received for image");
    }
}
