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

@ExtendWith(MockitoExtension.class)
class NextCommandHandlerTest {

    @Mock
    private transient ChatMessageSender chatMessageSender;
    @Mock
    private transient HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    private transient NextCommandHandler nextCommandHandler;

    @Test
    void shouldHandleNextCommand() {
        // given
        final String expectedMessage = "5 hours until the next hydration break.";
        final Instant nextHydrateBreak = LocalDateTime.now()
                .plusHours(5)
                .toInstant(ZoneOffset.UTC);
        given(hydrateReminderPlugin.getNextHydrateReminderInstant())
                .willReturn(nextHydrateBreak);
        given(hydrateReminderPlugin.getTimeDisplay(any()))
                .willReturn("5 hours");

        // when
        nextCommandHandler.handle();

        // then
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedMessage);
    }
}
