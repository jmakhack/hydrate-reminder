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

@ExtendWith(MockitoExtension.class)
class HydrateReminderPluginTest {
    @Mock
    private transient Client client;
    @Mock
    private transient HydrateReminderConfig config;
    @InjectMocks
    private transient HydrateReminderPlugin hydrateReminderPlugin;

    @Test
    void initShouldReturnZeroHydrationBreaksForTheCurrentSession() {
        assertEquals(0, hydrateReminderPlugin.getCurrentSessionHydrationBreaks());
    }

    @Test
    void shouldIncrementNumberOfHydrationBreaksForTheCurrentSession() {
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();
        assertEquals(3, hydrateReminderPlugin.getCurrentSessionHydrationBreaks());
    }

    @Test
    void shouldIncrementNumberOfHydrationBreaksTakenByHydrateForTheCurrentSession() {
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        assertEquals(3, hydrateReminderPlugin.getCurrentSessionHydrationBreaks());
    }

    @Test
    void initShouldSetTheCorrectResetState() {
        assertFalse(hydrateReminderPlugin.isResetState());
    }

    @Test
    void shouldSetTheCorrectResetState() {
        hydrateReminderPlugin.setResetState(true);
        assertTrue(hydrateReminderPlugin.isResetState());
        hydrateReminderPlugin.setResetState(false);
        assertFalse(hydrateReminderPlugin.isResetState());
    }

    @ParameterizedTest
    @CsvSource({
            "1 hour 1 minute 1 second, 3661",
            "19 hours 15 minutes 39 seconds, 69_339",
            "15 minutes 39 seconds, 939",
            "1 hour 0 minutes 0 seconds, 3600",
            "0 seconds, 0"
    })
    void shouldReturnCorrectStringFormatOfTheTime(String expectedString, long seconds) {
        assertEquals(expectedString, hydrateReminderPlugin.getTimeDisplay(Duration.ofSeconds(seconds)));
    }

    @Test
    void shouldReturnNoDurationWhenThereIsNoLastBreak() {
        final Optional<Duration> timeSinceLastBreak = hydrateReminderPlugin.getDurationSinceLastBreak(Optional.empty(), Instant.now());
        assertFalse(timeSinceLastBreak.isPresent());
    }

    @Test
    void shouldReturnDurationWhenThereIsLastBreak() {
        final Optional<Instant> timeOfLastBreak = Optional.of(Instant.now());
        final Optional<Duration> timeSinceLastBreak = hydrateReminderPlugin.getDurationSinceLastBreak(timeOfLastBreak, Instant.now());
        assertTrue(timeSinceLastBreak.isPresent());
    }

    @Test
    void shouldSetLastHydrateInstantAfterHydrateResetHasOccurred() {
        assertFalse(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
        hydrateReminderPlugin.resetHydrateReminderTimeInterval();
        assertTrue(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
    }

    @Test
    void shouldSetLastHydrateInstantAfterHydrateBreakHasOccurred() {
        assertFalse(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        assertTrue(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
    }

    @Test
    void shouldGetCorrectDurationFromLastBreakTillNowWhenThereIsLastBreak() {
        Instant nowInstant = Instant.now();
        Instant lastBreakInstant = nowInstant.minus(1, ChronoUnit.HOURS);

        Duration expectedDuration = Duration.ofHours(1);

        final Optional<Duration> timeSinceLastBreak = hydrateReminderPlugin.getDurationSinceLastBreak(Optional.of(lastBreakInstant), nowInstant);

        assertEquals(expectedDuration, timeSinceLastBreak.get());

    }

    @Test
    void shouldPlayAnimationWhenIncrementingNumberOfHydrationBreaksAndConfigEnabled() {
        // given
        final Player playerMock = mock(Player.class);
        given(config.hydrateAnimationEnabled()).willReturn(true);
        given(client.getLocalPlayer()).willReturn(playerMock);

        // when
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();

        // then
        verify(client).getLocalPlayer();
        verify(playerMock).setAnimation(AnimationID.CONSUMING);
    }

    @Test
    void shouldNotPlayAnimationWhenIncrementingNumberOfHydrationBreaksAndConfigDisabled() {
        // given
        given(config.hydrateAnimationEnabled()).willReturn(false);

        // when
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();

        // then
        verifyNoInteractions(client);
    }
}
