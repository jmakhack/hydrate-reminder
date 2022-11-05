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

/**
 * <p>The unit tests for the total command handler logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class TotalCommandHandlerTest
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
     * Mock total command handler
     */
    @InjectMocks
    private transient TotalCommandHandler totalCommandHandler;

    /**
     * <p>Tests that the total command handler outputs the proper message
     * when a single hydration break has occurred for the current session
     * </p>
     */
    @Test
    /* default */ void shouldSendHydrateWithSingularBreak()
    {
        when(hydrateReminderPlugin.getCurrentSessionHydrationBreaks()).thenReturn(1);
        totalCommandHandler.handle();
        final String expected = "Current session: 1 hydration break.";
        verify(hydrateReminderPlugin,times(1)).getCurrentSessionHydrationBreaks();
        verify(chatMessageSender,times(1)).sendHydrateEmojiChatGameMessage(expected);
    }

    /**
     * <p>Tests that the total command handler outputs the proper messages
     * for a variety of different hydration break amounts for all time
     * </p>
     * @param totalBreaks the total amount of breaks that have occurred
     */
    @ParameterizedTest
    @ValueSource(ints = {4, 7, 20})
    /* default */ void shouldSendBreaksForAllTime(final int totalBreaks)
    {
        when(hydrateReminderPlugin.getAllTimeHydrationBreaks()).thenReturn(totalBreaks);
        totalCommandHandler.handle();
        final String expected = String.format("All time: %d hydration breaks.", totalBreaks);
        verify(hydrateReminderPlugin).getAllTimeHydrationBreaks();
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expected);
    }

    /**
     * <p>Tests that the total command handler outputs the proper message
     * when more than one hydration breaks have occurred for the current session
     * </p>
     */
    @Test
    /* default */ void shouldSendHydrateWithPluralBreak()
    {
        when(hydrateReminderPlugin.getCurrentSessionHydrationBreaks()).thenReturn(2);
        totalCommandHandler.handle();
        final String expected = "Current session: 2 hydration breaks.";
        verify(hydrateReminderPlugin,times(1)).getCurrentSessionHydrationBreaks();
        verify(chatMessageSender,times(1)).sendHydrateEmojiChatGameMessage(expected);
    }
}
