package com.hydratereminder;

import net.runelite.api.AnimationID;
import net.runelite.api.Client;
import net.runelite.api.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class HydrateReminderPluginTest
{
    @Mock
    private Client client;
    @Mock
    private HydrateReminderConfig config;
    @InjectMocks
    private HydrateReminderPlugin hydrateReminderPlugin;

    @Before
    public void setup()
    {
        setupDefaultConfiguration();
    }

    private void setupDefaultConfiguration() {
        given(config.hydrateAnimationEnabled())
                .willReturn(false);
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
    public void shouldIncrementNumberOfHydrationBreaksTakenByHydrateForTheCurrentSession()
    {
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
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
    public void shouldSetLastHydrateInstantAfterHydrateResetHasOccurred()
    {
        assertFalse(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
        hydrateReminderPlugin.resetHydrateReminderTimeInterval();
        assertTrue(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
    }

    @Test
    public void shouldSetLastHydrateInstantAfterHydrateBreakHasOccurred()
    {
        assertFalse(hydrateReminderPlugin.getLastHydrateInstant().isPresent());
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
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

    @Test
    public void shouldPlayAnimationWhenIncrementingNumberOfHydrationBreaksAndConfigEnabled()
    {
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
    public void shouldNotPlayAnimationWhenIncrementingNumberOfHydrationBreaksAndConfigDisabled()
    {
        // given
        given(config.hydrateAnimationEnabled()).willReturn(false);

        // when
        hydrateReminderPlugin.incrementCurrentSessionHydrationBreaks();

        // then
        verifyNoInteractions(client);
    }
}
