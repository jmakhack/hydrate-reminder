package com.hydratereminder;

import com.hydratereminder.images.HydrateReminderImages;
import org.junit.jupiter.api.Test;

import static net.runelite.api.ItemID.BOTTLED_WATER;
import static net.runelite.api.ItemID.COCONUT_MILK;
import static net.runelite.api.ItemID.PURPLE_DYE;
import static net.runelite.api.ItemID.RUM;
import static net.runelite.api.ItemID.TEAPOT;
import static net.runelite.api.ItemID.WATERMELON_SLICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrateReminderImagesTest {
    @Test
    void testTimerImagesToString() {
        assertEquals("Cup of Water", HydrateReminderImages.CUP_OF_WATER_IMAGE.toString());
        assertEquals("Cup of Tea", HydrateReminderImages.CUP_OF_TEA_IMAGE.toString());
        assertEquals("Beer", HydrateReminderImages.BEER_IMAGE.toString());
        assertEquals("Redberry Juice", HydrateReminderImages.REDBERRY_JUICE_IMAGE.toString());
        assertEquals("Super Restore", HydrateReminderImages.SUPER_RESTORE_IMAGE.toString());
        assertEquals("Holy Elixir", HydrateReminderImages.HOLY_ELIXIR_IMAGE.toString());
    }

    @Test
    void testTimerImagesGetID() {
        assertEquals(BOTTLED_WATER, HydrateReminderImages.BOTTLED_WATER_IMAGE.getID());
        assertEquals(TEAPOT, HydrateReminderImages.TEAPOT_IMAGE.getID());
        assertEquals(RUM, HydrateReminderImages.RUM_IMAGE.getID());
        assertEquals(COCONUT_MILK, HydrateReminderImages.COCONUT_MILK_IMAGE.getID());
        assertEquals(WATERMELON_SLICE, HydrateReminderImages.WATERMELON_SLICE_IMAGE.getID());
        assertEquals(PURPLE_DYE, HydrateReminderImages.PURPLE_DYE_IMAGE.getID());
    }
}
