package com.hydratereminder.command.help;

import com.hydratereminder.chat.ChatMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
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
