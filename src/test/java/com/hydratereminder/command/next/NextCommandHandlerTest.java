package com.hydratereminder.command.next;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the next command handler logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class NextCommandHandlerTest
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
     * Mock next command handler
     */
    @InjectMocks
    private transient NextCommandHandler nextCommandHandler;

    /**
     * <p>Tests that the next command outputs the proper message
     * </p>
     */
    @Test
    /* default */ void shouldHandleNextCommand()
    {
        final String expectedMessage = "5 hours until the next hydration break.";
        final Instant nextHydrateBreak = LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.UTC);
        given(hydrateReminderPlugin.getNextHydrateReminderInstant()).willReturn(nextHydrateBreak);
        given(hydrateReminderPlugin.getTimeDisplay(any())).willReturn("5 hours");
        nextCommandHandler.handle();
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedMessage);
    }
}
