package com.hydratereminder;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.Assert.*;

public class HydrateReminderPluginTest
{
    private HydrateReminderPlugin hydrateReminderPlugin;

    @Before
    public void setupHydrateReminderPlugin()
    {
        hydrateReminderPlugin = new HydrateReminderPlugin();
    }

    @Test
    public void initShouldReturnZeroHydrationBreaksForTheCurrentSession()
    {
        assertEquals(0, hydrateReminderPlugin.getCurrentSessionHydrationBreaks());
    }

    @Test
    public void shouldIncrementNumberOfHydrationBreaksForTheCurrentSession()
    {
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        assertEquals(3, hydrateReminderPlugin.getCurrentSessionHydrationBreaks());
    }

    @Test
    public void initShouldSetTheCorrectResetState()
    {
        assertFalse(hydrateReminderPlugin.isResetState());
    }

    @Test
    public void shouldSetTheCorrectResetState()
    {
        hydrateReminderPlugin.setResetState(true);
        assertTrue(hydrateReminderPlugin.isResetState());
        hydrateReminderPlugin.setResetState(false);
        assertFalse(hydrateReminderPlugin.isResetState());
    }

    @Test
    public void shouldReturnCorrectStringFormatOfTheTime()
    {
        assertEquals("1 hour 1 minute 1 second",
                hydrateReminderPlugin.getTimeDisplay(Duration.ofSeconds(3661)));
        assertEquals("19 hours 15 minutes 39 seconds",
                hydrateReminderPlugin.getTimeDisplay(Duration.ofSeconds(69339)));
        assertEquals("15 minutes 39 seconds",
                hydrateReminderPlugin.getTimeDisplay(Duration.ofSeconds(939)));
        assertEquals("1 hour 0 minutes 0 seconds",
                hydrateReminderPlugin.getTimeDisplay(Duration.ofHours(1)));
        assertEquals("0 seconds",
                hydrateReminderPlugin.getTimeDisplay(Duration.ofSeconds(0)));
    }

    @Test
    public void shouldReturnDifferentMessageWhenThereIsNoTimeSinceLastBreak()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.empty();
        final String prevCommandMessage = hydrateReminderPlugin.formatHandleHydratePrevCommand(timeSinceLastBreak);
        assertEquals("No hydration breaks have been taken yet.", prevCommandMessage);
    }

    @Test
    public void shouldReturnDifferentMessageWhenThereIsResetSinceLastBreak()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofSeconds(645));
        hydrateReminderPlugin.setResetState(true);
        final String prevCommandMessage = hydrateReminderPlugin.formatHandleHydratePrevCommand(timeSinceLastBreak);
        assertEquals("10 minutes 45 seconds since the last hydration interval reset.", prevCommandMessage);
    }

    @Test
    public void shouldReturnNoDurationWhenThereIsNoLastBreak()
    {
        final Optional<Duration> timeSinceLastBreak = hydrateReminderPlugin.getDurationSinceLastBreak(Optional.empty(), Instant.now());
        assertFalse(timeSinceLastBreak.isPresent());
    }

    @Test
    public void shouldReturnDurationWhenThereIsLastBreak()
    {
        final Optional<Instant> timeOfLastBreak = Optional.of(Instant.now());
        final Optional<Duration> timeSinceLastBreak = hydrateReminderPlugin.getDurationSinceLastBreak(timeOfLastBreak, Instant.now());
        assertTrue(timeSinceLastBreak.isPresent());
    }

    @Test
    public void shouldReturnCorrectStringFormatOfHandleHydratePrevCommandMessage()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofMinutes(130));
        final String prevCommandMessage = hydrateReminderPlugin.formatHandleHydratePrevCommand(timeSinceLastBreak);
        assertEquals("2 hours 10 minutes 0 seconds since the last hydration break.", prevCommandMessage);
    }

    @Test
    public void shouldSetLastHydrateInstantAfterHydrateBreakHasOccurred()
    {
        assertFalse(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
        hydrateReminderPlugin.resetHydrateReminderTimeInterval();
        assertTrue(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
    }

    @Test
    public void shoudldGetCorrectDurationFromLastBreakTillNowWhenThereIsLastBreak()
    {
        Instant nowInstant = Instant.now();
        Instant lastBreakInstant = nowInstant.minus(1, ChronoUnit.HOURS);

        Duration expectedDuration = Duration.ofHours(1);

        final Optional<Duration> timeSinceLastBreak = hydrateReminderPlugin.getDurationSinceLastBreak(Optional.of(lastBreakInstant), nowInstant);

        assertEquals(expectedDuration, timeSinceLastBreak.get());

    }
}
