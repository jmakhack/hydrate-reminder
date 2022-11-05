package com.hydratereminder.command.hydrate;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the hydrate command handler logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class HydrateCommandHandlerTest
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
     * Mock hydrate command handler
     */
    @InjectMocks
    private transient HydrateCommandHandler hydrateCommandHandler;

    /**
     * <p>Tests that the hydrate command resets the current hydration
     * interval and increments the number of hydration breaks taken
     * </p>
     */
    @Test
    /* default */ void shouldResetCurrentHydrateIntervalAndIncreaseHydrationBreaksWhenHydrateCommandIsCalled()
    {
        hydrateCommandHandler.handle();
        verify(hydrateReminderPlugin).hydrateBetweenHydrationBreaks();
        verify(hydrateReminderPlugin).setResetState(true);
    }

    /**
     * <p>Tests that the proper message is displayed whenever the
     * hydrate command is called
     * </p>
     */
    @Test
    /* default */ void shouldSendProperMessageWhenHydrateCommandIsCalled()
    {
        final String message = "Successfully hydrated before reminder interval finished";
        hydrateCommandHandler.handle();
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(message);
    }
}
