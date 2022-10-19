package com.hydratereminder.command.total;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TotalCommandHandlerTest {

    @Mock
    private transient ChatMessageSender chatMessageSender;
    @Mock
    private transient HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    private transient TotalCommandHandler totalCommandHandler;

    @Test
    void shouldSendHydrateWithSingularBreak() {
        when(hydrateReminderPlugin.getCurrentSessionHydrationBreaks()).thenReturn(1);
        totalCommandHandler.handle();

        final String expected = "Current session: 1 hydration break.";
        verify(hydrateReminderPlugin,times(1)).getCurrentSessionHydrationBreaks();
        verify(chatMessageSender,times(1)).sendHydrateEmojiChatGameMessage(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 7, 20})
    void shouldSendBreaksForAllTime(final int totalBreaks) {
        when(hydrateReminderPlugin.getAllTimeHydrationBreaks()).thenReturn(totalBreaks);
        totalCommandHandler.handle();

        final String expected = String.format("All time: %d hydration breaks.", totalBreaks);
        verify(hydrateReminderPlugin).getAllTimeHydrationBreaks();
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expected);
    }

    @Test
    void shouldSendHydrateWithPluralBreak() {
        when(hydrateReminderPlugin.getCurrentSessionHydrationBreaks()).thenReturn(2);
        totalCommandHandler.handle();

        final String expected = "Current session: 2 hydration breaks.";
        verify(hydrateReminderPlugin,times(1)).getCurrentSessionHydrationBreaks();
        verify(chatMessageSender,times(1)).sendHydrateEmojiChatGameMessage(expected);
    }
}
