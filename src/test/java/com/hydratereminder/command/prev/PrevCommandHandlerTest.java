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

@ExtendWith(MockitoExtension.class)
public class PrevCommandHandlerTest {

    @Mock
    private transient ChatMessageSender chatMessageSender;

    @Mock
    private transient HydrateReminderPlugin hydrateReminderPlugin;

    @Mock
    private transient Clock clock;

    @InjectMocks
    private transient PrevCommandHandler prevCommandHandler;


    @Test
    public void shouldSendMessageSinceLastHydrationInterval() {
        //given
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofMinutes(100));
        final String timeSinceLastBreakAsString = "1 hours 40 minutes 0 seconds";
        final Optional<Instant> lastHydrateTime = Optional.of(Instant.now().minus(10, ChronoUnit.MINUTES));

        given(clock.instant()).willReturn(Instant.MAX);
        given(hydrateReminderPlugin.getLastHydrateInstant()).willReturn(lastHydrateTime);
        given(hydrateReminderPlugin.getDurationSinceLastBreak(lastHydrateTime, Instant.now(clock))).willReturn(timeSinceLastBreak);
        given(hydrateReminderPlugin.getTimeDisplay(timeSinceLastBreak.get())).willReturn(timeSinceLastBreakAsString);
        given(hydrateReminderPlugin.isResetState()).willReturn(true);

        String expectedMessage = "1 hours 40 minutes 0 seconds since the last hydration interval reset.";

        //when
        prevCommandHandler.handle();

        //then
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedMessage);
    }

    @Test
    public void shouldReturnExpectedMessageWhenThereIsNoTimeSinceLastBreak() {
        //given
        final Optional<Duration> timeSinceLastBreak = Optional.empty();

        String expectedMessage = "No hydration breaks have been taken yet.";

        //when
        final String prevCommandMessage = prevCommandHandler.formatHandleHydratePrevCommand(timeSinceLastBreak);

        //then
        assertEquals(expectedMessage, prevCommandMessage);
    }

    @Test
    public void shouldReturnExpectedMessageWhenThereIsResetSinceLastBreak() {
        //given
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofSeconds(645));
        final String timeSinceLastBreakAsString = "10 minutes 45 seconds";

        given(hydrateReminderPlugin.getTimeDisplay(timeSinceLastBreak.get())).willReturn(timeSinceLastBreakAsString);
        given(hydrateReminderPlugin.isResetState()).willReturn(true);

        String expectedMessage = "10 minutes 45 seconds since the last hydration interval reset.";

        //when
        final String prevCommandMessage = prevCommandHandler.formatHandleHydratePrevCommand(timeSinceLastBreak);

        //then
        assertEquals(expectedMessage, prevCommandMessage);
    }

    @Test
    public void shouldReturnCorrectStringFormatOfHandleHydratePrevCommandMessage() {
        //given
        final Optional<Duration> timeSinceLastBreak = Optional.of(Duration.ofMinutes(130));
        final String timeSinceLastBreakAsString = "2 hours 10 minutes 0 seconds";

        given(hydrateReminderPlugin.getTimeDisplay(timeSinceLastBreak.get())).willReturn(timeSinceLastBreakAsString);
        given(hydrateReminderPlugin.isResetState()).willReturn(false);

        String expectedStringFormat = "2 hours 10 minutes 0 seconds since the last hydration break.";

        //when
        final String prevCommandMessage = prevCommandHandler.formatHandleHydratePrevCommand(timeSinceLastBreak);

        //then
        assertEquals(expectedStringFormat, prevCommandMessage);
    }

}

