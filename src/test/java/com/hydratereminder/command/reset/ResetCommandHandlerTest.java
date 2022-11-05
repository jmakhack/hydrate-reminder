package com.hydratereminder.command.reset;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the reset command handler logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class ResetCommandHandlerTest
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
     * Mock reset command handler
     */
    @InjectMocks
    private transient ResetCommandHandler resetCommandHandler;

    /**
     * <p>Tests that the reset command works as expected
     * </p>
     */
    @Test
    /* default */ void shouldResetHydrate()
    {
        resetCommandHandler.handle();
        final String resetString = "Hydrate reminder interval has been successfully reset.";
        verify(hydrateReminderPlugin, times(1)).setLastHydrateInstant(any());
        verify(hydrateReminderPlugin, times(1)).setResetState(true);
        verify(chatMessageSender, times(1)).sendHydrateEmojiChatGameMessage(resetString);
    }
}
