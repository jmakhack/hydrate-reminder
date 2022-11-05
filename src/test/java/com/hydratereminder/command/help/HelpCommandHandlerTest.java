package com.hydratereminder.command.help;

import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the help command handler logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class HelpCommandHandlerTest
{

    /**
     * Mock chat message sender
     */
    @Mock
    private transient ChatMessageSender chatMessageSender;

    /**
     * Mock help command handler
     */
    @InjectMocks
    private transient HelpCommandHandler helpCommandHandler;

    /**
     * <p>Tests that the help command outputs the proper message
     * </p>
     */
    @Test
    void shouldHandleHelpCommand()
    {
        final String possibleCommands = "next, prev, reset, hydrate, help, total";
        final String expectedMessage = "Available commands: ::hr " + possibleCommands;
        helpCommandHandler.handle();
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedMessage);
    }
}
