package com.hydratereminder;

import net.runelite.api.AnimationID;
import net.runelite.api.Client;
import net.runelite.api.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * <p>The unit tests for the main hydrate reminder plugin logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class HydrateReminderPluginTest
{

    /**
     * Mock RuneLite client
     */
    @Mock
    private transient Client client;

    /**
     * Mock hydrate reminder config
     */
    @Mock
    private transient HydrateReminderConfig config;

    /**
     * Mock hydrate reminder plugin
     */
    @InjectMocks
    private transient HydrateReminderPlugin hydrateReminderPlugin;

    /**
     * <p>Tests that the number of hydration breaks is properly initialized
     * at zero
     * </p>
     */
    @Test
    /* default */ void initShouldReturnZeroHydrationBreaksForTheCurrentSession()
    {
        assertEquals(0, hydrateReminderPlugin.getCurrentSessionHydrationBreaks(),
                "Expected number of current session hydration breaks to equal 0");
    }

    /**
     * <p>Tests that the number of hydration breaks can be properly
     * incremented
     * </p>
     */
    @Test
    /* default */ void shouldIncrementNumberOfHydrationBreaksForTheCurrentSession()
    {
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        assertEquals(3, hydrateReminderPlugin.getCurrentSessionHydrationBreaks(),
                "Expected number of current session hydration breaks to equal 3");
    }

    /**
     * <p>Tests that the number of hydration breaks gets incremented when
     * hydration between hydration breaks occurs
     * </p>
     */
    @Test
    /* default */ void shouldIncrementNumberOfHydrationBreaksTakenByHydrateForTheCurrentSession()
    {
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        assertEquals(4, hydrateReminderPlugin.getCurrentSessionHydrationBreaks(),
                "Expected number of current session hydration breaks to equal 4");
    }

    /**
     * <p>Tests that the reset state is properly initialized to false
     * </p>
     */
    @Test
    /* default */ void initShouldSetTheCorrectResetState()
    {
        assertFalse(hydrateReminderPlugin.isResetState(), "Expected reset state to be false initially");
    }

    /**
     * <p>Tests that the reset state can be set to true
     * </p>
     */
    @Test
    /* default */ void shouldBeAbleToSetResetStateToTrue()
    {
        hydrateReminderPlugin.setResetState(true);
        assertTrue(hydrateReminderPlugin.isResetState(), "Expected reset state to be true");
    }

    /**
     * <p>Tests that the reset state can be set to false
     * </p>
     */
    @Test
    /* default */ void shouldBeAbleToSetResetStateToFalse()
    {
        hydrateReminderPlugin.setResetState(false);
        assertFalse(hydrateReminderPlugin.isResetState(), "Expected reset state to be false");
    }

    /**
     * <p>Tests that the time format is displayed properly for a variety of
     * different durations
     * </p>
     * @param expectedString the expected time formatted string
     * @param seconds the number of seconds to have time formatted
     */
    @ParameterizedTest
    @CsvSource({
            "1 hour 1 minute 1 second, 3_661",
            "19 hours 15 minutes 39 seconds, 69_339",
            "15 minutes 39 seconds, 939",
            "1 hour 0 minutes 0 seconds, 3_600",
            "0 seconds, 0"
    })
    /* default */ void shouldReturnCorrectStringFormatOfTheTime(final String expectedString, final long seconds)
    {
        assertEquals(expectedString, hydrateReminderPlugin.getTimeDisplay(Duration.ofSeconds(seconds)),
                "Unexpected time format received");
    }

    /**
     * <p>Tests that no duration is returned when there is no previous
     * hydration break taken
     * </p>
     */
    @Test
    /* default */ void shouldReturnNoDurationWhenThereIsNoLastBreak()
    {
        final Optional<Duration> timeSinceLastBreak =
                hydrateReminderPlugin.getDurationSinceLastBreak(Optional.empty(), Instant.now());
        assertFalse(timeSinceLastBreak.isPresent(), "Expected time since last break to not exist");
    }

    /**
     * <p>Tests that duration is returned when there is a previous
     * hydration break taken
     * </p>
     */
    @Test
    /* default */ void shouldReturnDurationWhenThereIsLastBreak()
    {
        final Optional<Instant> timeOfLastBreak = Optional.of(Instant.now());
        final Optional<Duration> timeSinceLastBreak =
                hydrateReminderPlugin.getDurationSinceLastBreak(timeOfLastBreak, Instant.now());
        assertTrue(timeSinceLastBreak.isPresent(), "Expected time since last break to exist");
    }

    /**
     * <p>Tests that the last hydrate instant is initially not present
     * </p>
     */
    @Test
    /* default */ void shouldSetLastHydrateInstantToFalseInitially()
    {
        assertFalse(hydrateReminderPlugin.getLastHydrateInstant().isPresent(),
                "Expected last hydrate instant to not exist initially");
    }

    /**
     * <p>Tests that last hydrate instant exists after a hydrate
     * interval reset has occurred
     * </p>
     */
    @Test
    /* default */ void shouldSetLastHydrateInstantAfterHydrateResetHasOccurred()
    {
        hydrateReminderPlugin.resetHydrateReminderTimeInterval();
        assertTrue(hydrateReminderPlugin.getLastHydrateInstant().isPresent(),
                "Expected last hydrate instant to exist after reset");
    }

    /**
     * <p>Tests that last hydrate instant exists after a hydrate
     * between hydration breaks has occurred
     * </p>
     */
    @Test
    /* default */ void shouldSetLastHydrateInstantAfterHydrateBreakHasOccurred()
    {
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        assertTrue(hydrateReminderPlugin.getLastHydrateInstant().isPresent(),
                "Expected last hydrate instant to exist after break");
    }

    /**
     * <p>Tests that correct duration is returned when calculating the
     * duration since the last hydration break
     * </p>
     */
    @Test
    /* default */ void shouldGetCorrectDurationFromLastBreakTillNowWhenThereIsLastBreak()
    {
        final Instant nowInstant = Instant.now();
        final Instant lastBreakInstant = nowInstant.minus(1, ChronoUnit.HOURS);
        final Duration expectedDuration = Duration.ofHours(1);
        final Optional<Duration> timeSinceLastBreak =
                hydrateReminderPlugin.getDurationSinceLastBreak(Optional.of(lastBreakInstant), nowInstant);
        assertEquals(expectedDuration, timeSinceLastBreak.get(),
                "Unexpected duration since last break received");
    }

    /**
     * <p>Tests that an animation is played whenever the hydration break
     * number is incremented and animation settings are enabled
     * </p>
     */
    @Test
    /* default */ void shouldPlayAnimationWhenIncrementingNumberOfHydrationBreaksAndConfigEnabled()
    {
        final Player playerMock = mock(Player.class);
        given(config.hydrateAnimationEnabled()).willReturn(true);
        given(client.getLocalPlayer()).willReturn(playerMock);
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        verify(client).getLocalPlayer();
        verify(playerMock).setAnimation(AnimationID.CONSUMING);
    }

    /**
     * <p>Test that an animation is not played whenever the hydration break
     * number is incremented and animation settings are disabled
     * </p>
     */
    @Test
    /* default */ void shouldNotPlayAnimationWhenIncrementingNumberOfHydrationBreaksAndConfigDisabled()
    {
        given(config.hydrateAnimationEnabled()).willReturn(false);
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        verifyNoInteractions(client);
    }
}
