package com.hydratereminder.command.prev;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the prev command handler logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class PrevCommandHandlerTest
{

    /**
     * Mock chat message sender
     */
    @Mock
    private transient ChatMessageSender chatMessageSender;

    /**
     * Mock hydrate reminder plugin
     */
    @Mock
    private transient HydrateReminderPlugin hydrateReminderPlugin;

    /**
     * Mock clock
     */
    @Mock
    private transient Clock clock;

    /**
     * Mock prev command handler
     */
    @InjectMocks
    private transient PrevCommandHandler prevCommandHandler;

    /**
     * <p>Tests that the prev command sends the appropriate message
     * containing the duration since the last hydration interval reset
     * </p>
     */
    @Test
    /* default */ void shouldSendMessageSinceLastHydrationInterval()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofMinutes(100));
        final String timeSinceLastBreakAsString = "1 hours 40 minutes 0 seconds";
        final Optional<Instant> lastHydrateTime =
                Optional.of(Instant.now().minus(10, ChronoUnit.MINUTES));
        given(clock.instant()).willReturn(Instant.MAX);
        given(hydrateReminderPlugin.getLastHydrateInstant()).willReturn(lastHydrateTime);
        given(hydrateReminderPlugin.getDurationSinceLastBreak(lastHydrateTime, Instant.now(clock)))
                .willReturn(timeSinceLastBreak);
        given(hydrateReminderPlugin.getTimeDisplay(timeSinceLastBreak.get())).willReturn(timeSinceLastBreakAsString);
        given(hydrateReminderPlugin.isResetState()).willReturn(true);
        final String expectedMessage = "1 hours 40 minutes 0 seconds since the last hydration interval reset.";
        prevCommandHandler.handle();
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedMessage);
    }

    /**
     * <p>Tests that the prev command sends the appropriate message
     * when there has been no previous hydration break taken
     * </p>
     */
    @Test
    /* default */ void shouldReturnExpectedMessageWhenThereIsNoTimeSinceLastBreak()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.empty();
        final String expectedMessage = "No hydration breaks have been taken yet.";
        final String prevCommandMessage = prevCommandHandler.formatHandleHydratePrevCommand(timeSinceLastBreak);
        assertEquals(expectedMessage, prevCommandMessage,
                "Unexpected prev command message received when no hydration breaks taken");
    }

    /**
     * <p>Tests that the prev command sends the appropriate message
     * when there has been a hydration interval reset
     * </p>
     */
    @Test
    /* default */ void shouldReturnExpectedMessageWhenThereIsResetSinceLastBreak()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofSeconds(645));
        final String timeSinceLastBreakAsString = "10 minutes 45 seconds";
        given(hydrateReminderPlugin.getTimeDisplay(timeSinceLastBreak.get())).willReturn(timeSinceLastBreakAsString);
        given(hydrateReminderPlugin.isResetState()).willReturn(true);
        final String expectedMessage = "10 minutes 45 seconds since the last hydration interval reset.";
        final String prevCommandMessage = prevCommandHandler.formatHandleHydratePrevCommand(timeSinceLastBreak);
        assertEquals(expectedMessage, prevCommandMessage,
                "Unexpected prev command message received with reset");
    }

    /**
     * <p>Tests that the prev command sends the appropriate message
     * containing the duration since the last hydration interval
     * </p>
     */
    @Test
    /* default */ void shouldReturnCorrectStringFormatOfHandleHydratePrevCommandMessage()
    {
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofMinutes(130));
        final String timeSinceLastBreakAsString = "2 hours 10 minutes 0 seconds";
        given(hydrateReminderPlugin.getTimeDisplay(timeSinceLastBreak.get())).willReturn(timeSinceLastBreakAsString);
        given(hydrateReminderPlugin.isResetState()).willReturn(false);
        final String expectedStringFormat = "2 hours 10 minutes 0 seconds since the last hydration break.";
        final String prevCommandMessage = prevCommandHandler.formatHandleHydratePrevCommand(timeSinceLastBreak);
        assertEquals(expectedStringFormat, prevCommandMessage,
                "Unexpected prev command message format received");
    }
}
