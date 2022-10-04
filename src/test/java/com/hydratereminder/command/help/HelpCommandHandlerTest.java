package com.hydratereminder.command.help;

import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HelpCommandHandlerTest {

    @Mock
    private ChatMessageSender chatMessageSender;
    @InjectMocks
    private HelpCommandHandler helpCommandHandler;

    @Test
    public void shouldHandleHelpCommand() {
        // given
        final String possibleCommands = "next, prev, reset, hydrate, help, total";
        final String expectedMessage = "Available commands: ::hr " + possibleCommands;

        // when
        helpCommandHandler.handle();

        // then
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedMessage);
    }

}
