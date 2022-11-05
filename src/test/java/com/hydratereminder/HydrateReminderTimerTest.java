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

/**
 * <p>Mock HydrateReminderPlugin class
 * </p>
 */
class MockHydrateReminderPlugin extends HydrateReminderPlugin
{

    /**
     * The next hydrate reminder instant
     */
    public transient Instant instant;

    /**
     * <p>Gets the next hydrate reminder instant
     * </p>
     * @return the next hydrate reminder instant
     */
    @Override
    public Instant getNextHydrateReminderInstant()
    {
        return instant;
    }
}

/**
 * <p>The unit tests for the hydrate reminder timer logic
 * </p>
 */
class HydrateReminderTimerTest
{

    /**
     * Mock hydrate reminder plugin
     */
    private transient MockHydrateReminderPlugin mockHydrateReminderPlugin;

    /**
     * Hydrate reminder timer
     */
    private transient HydrateReminderTimer hydrateReminderTimer;

    /**
     * <p>Setup tests by creating new mock plugin and timer
     * </p>
     */
    @BeforeEach
    void setupHydrateReminderPlugin()
    {
        mockHydrateReminderPlugin = new MockHydrateReminderPlugin();
        final BufferedImage timerImage = ImageUtil.loadImageResource(getClass(), "water_icon.png");
        hydrateReminderTimer = new HydrateReminderTimer(mockHydrateReminderPlugin, timerImage, WHITE);
    }

    /**
     * <p>Tests that the text color is initialized properly
     * </p>
     */
    @Test
    void shouldSetInitializedTextColor()
    {
        assertEquals(hydrateReminderTimer.getTextColor(), WHITE, "Expected text color to be set to WHITE");
    }

    /**
     * <p>Tests that the timer can be successfully rendered
     * </p>
     */
    @Test
    void shouldReturnTrueOnRender()
    {
        assertTrue(hydrateReminderTimer.render(), "Expected render method to return true");
    }

    /**
     * <p>Tests that the timer can be successfully culled
     * </p>
     */
    @Test
    void shouldReturnFalseOnCull()
    {
        assertFalse(hydrateReminderTimer.cull(), "Expected cull method to return false");
    }

    /**
     * <p>Tests that the timer has the correctly set name
     * </p>
     */
    @Test
    void shouldHaveNameBasedOnPluginAndClassName()
    {
        final String pluginName = "MockHydrateReminderPlugin";
        final String className = "HydrateReminderTimer";
        final String finalName = String.join("_", pluginName, className);
        assertEquals(hydrateReminderTimer.getName(), finalName, "Unexpected timer name received");
    }

    /**
     * <p>Tests that the minutes and seconds display is displayed properly
     * </p>
     */
    @Test
    void shouldDisplayMinutesAndSecondsWhenTimeLeftIsOverAMinute()
    {
        mockHydrateReminderPlugin.instant = Instant.now().plus(Duration.ofMinutes(90));
        assertTrue(hydrateReminderTimer.getText().matches("^\\d{2}:\\d{2}$"),
                "Expected proper minutes seconds time format");
    }

    /**
     * <p>Tests that the minutes is properly displayed as zero when under a minute
     * remains on the hydrate timer
     * </p>
     */
    @Test
    void shouldDisplayZeroMinutesWhenTimeLeftIsUnderAMinute()
    {
        mockHydrateReminderPlugin.instant = Instant.now().plus(Duration.ofSeconds(45));
        assertTrue(hydrateReminderTimer.getText().matches("^0:\\d{2}$"),
                "Expected proper seconds time format");
    }

    /**
     * <p>Tests that the timer displays zeros if time remaining is negative
     * </p>
     */
    @Test
    void shouldDisplayZeroMinutesAndSecondsWhenTimeLeftIsNegative()
    {
        mockHydrateReminderPlugin.instant = Instant.now().minus(Duration.ofSeconds(150));
        assertEquals("0:00", hydrateReminderTimer.getText(), "Expected timer text to read 0:00");
    }
}
