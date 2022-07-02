package com.hydratereminder;

import org.junit.Test;

import static net.runelite.api.ItemID.*;
import static org.junit.Assert.assertEquals;

public class HydrateReminderTimerImagesTest
{
    @Test
    public void testTimerImagesToString()
    {
        assertEquals("Cup of Water", HydrateReminderTimerImages.CUP_OF_WATER_IMAGE.toString());
        assertEquals("Cup of Tea", HydrateReminderTimerImages.CUP_OF_TEA_IMAGE.toString());
        assertEquals("Beer", HydrateReminderTimerImages.BEER_IMAGE.toString());
        assertEquals("Redberry Juice", HydrateReminderTimerImages.REDBERRY_JUICE_IMAGE.toString());
        assertEquals("Super Restore", HydrateReminderTimerImages.SUPER_RESTORE_IMAGE.toString());
        assertEquals("Holy Elixir", HydrateReminderTimerImages.HOLY_ELIXIR_IMAGE.toString());
    }

    @Test
    public void testTimerImagesGetID()
    {
        assertEquals(BOTTLED_WATER, HydrateReminderTimerImages.BOTTLED_WATER_IMAGE.getID());
        assertEquals(TEAPOT, HydrateReminderTimerImages.TEAPOT_IMAGE.getID());
        assertEquals(RUM, HydrateReminderTimerImages.RUM_IMAGE.getID());
        assertEquals(COCONUT_MILK, HydrateReminderTimerImages.COCONUT_MILK_IMAGE.getID());
        assertEquals(WATERMELON_SLICE, HydrateReminderTimerImages.WATERMELON_SLICE_IMAGE.getID());
        assertEquals(PURPLE_DYE, HydrateReminderTimerImages.PURPLE_DYE_IMAGE.getID());
    }
}
