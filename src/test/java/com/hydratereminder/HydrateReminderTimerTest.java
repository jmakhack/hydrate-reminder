package com.hydratereminder;

import net.runelite.client.util.ImageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

import static java.awt.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockHydrateReminderPlugin extends HydrateReminderPlugin
{
    public Instant instant;

    @Override
    public Instant getNextHydrateReminderInstant()
    {
        return instant;
    }
}

public class HydrateReminderTimerTest
{
    private MockHydrateReminderPlugin mockHydrateReminderPlugin;

    private HydrateReminderTimer hydrateReminderTimer;

    @BeforeEach
    public void setupHydrateReminderPlugin()
    {
        mockHydrateReminderPlugin = new MockHydrateReminderPlugin();
        final BufferedImage timerImage = ImageUtil.loadImageResource(getClass(), "water_icon.png");
        hydrateReminderTimer = new HydrateReminderTimer(mockHydrateReminderPlugin, timerImage, WHITE);
    }

    @Test
    public void shouldSetInitializedTextColor()
    {
        assertEquals(hydrateReminderTimer.getTextColor(), WHITE);
    }

    @Test
    public void shouldReturnTrueOnRender()
    {
        assertTrue(hydrateReminderTimer.render());
    }

    @Test
    public void shouldReturnFalseOnCull()
    {
        assertFalse(hydrateReminderTimer.cull());
    }

    @Test
    public void shouldHaveNameBasedOnPluginAndClassName()
    {
        final String pluginName = "MockHydrateReminderPlugin";
        final String className = "HydrateReminderTimer";
        final String finalName = String.join("_", pluginName, className);
        assertEquals(hydrateReminderTimer.getName(), finalName);
    }

    @Test
    public void shouldDisplayMinutesAndSecondsWhenTimeLeftIsOverAMinute()
    {
        mockHydrateReminderPlugin.instant = Instant.now().plus(Duration.ofMinutes(90));
        assertTrue(hydrateReminderTimer.getText().matches("^\\d{2}:\\d{2}$"));
    }

    @Test
    public void shouldDisplayZeroMinutesWhenTimeLeftIsUnderAMinute()
    {
        mockHydrateReminderPlugin.instant = Instant.now().plus(Duration.ofSeconds(45));
        assertTrue(hydrateReminderTimer.getText().matches("^0:\\d{2}$"));
    }

    @Test
    public void shouldDisplayZeroMinutesAndSecondsWhenTimeLeftIsNegative()
    {
        mockHydrateReminderPlugin.instant = Instant.now().minus(Duration.ofSeconds(150));
        assertEquals(hydrateReminderTimer.getText(), "0:00");
    }
}
