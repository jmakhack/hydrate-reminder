package com.hydratereminder;

import com.hydratereminder.images.HydrateReminderImages;
import com.hydratereminder.images.ImageCategories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import static net.runelite.api.ItemID.BOTTLED_WATER;
import static net.runelite.api.ItemID.COCONUT_MILK;
import static net.runelite.api.ItemID.PURPLE_DYE;
import static net.runelite.api.ItemID.RUM;
import static net.runelite.api.ItemID.TEAPOT;
import static net.runelite.api.ItemID.WATERMELON_SLICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrateReminderImagesTest {
    @ParameterizedTest
    @CsvSource({
            "Cup of Water, CUP_OF_WATER_IMAGE",
            "Cup of Tea, CUP_OF_TEA_IMAGE",
            "Beer, BEER_IMAGE",
            "Redberry Juice, REDBERRY_JUICE_IMAGE",
            "Super Restore, SUPER_RESTORE_IMAGE",
            "Holy Elixir, HOLY_ELIXIR_IMAGE"
    })
    void testImagesToString(String expectedString, HydrateReminderImages image) {
        assertEquals(expectedString, image.toString());
    }

    @Test
    void testImagesGetID() {
        assertEquals(BOTTLED_WATER, HydrateReminderImages.BOTTLED_WATER_IMAGE.getID());
        assertEquals(TEAPOT, HydrateReminderImages.TEAPOT_IMAGE.getID());
        assertEquals(RUM, HydrateReminderImages.RUM_IMAGE.getID());
        assertEquals(COCONUT_MILK, HydrateReminderImages.COCONUT_MILK_IMAGE.getID());
        assertEquals(WATERMELON_SLICE, HydrateReminderImages.WATERMELON_SLICE_IMAGE.getID());
        assertEquals(PURPLE_DYE, HydrateReminderImages.PURPLE_DYE_IMAGE.getID());
    }

    @ParameterizedTest
    @CsvSource({
            "WATER, CIRCLET_OF_WATER_IMAGE",
            "TEA, NETTLE_TEA_IMAGE",
            "ALCOHOL, VODKA_IMAGE",
            "DRINKCONTAINERS, ICE_COOLER_IMAGE",
            "POTIONCATEGORY, PRAYER_MIX_IMAGE",
            "OTHER, FROZEN_JUG_IMAGE"
    })
    void testImagesGetCategory(ImageCategories category, HydrateReminderImages image) {
        assertEquals(category, image.getCategory());
    }
}
