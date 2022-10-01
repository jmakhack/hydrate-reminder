package com.hydratereminder.command.total;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TotalCommandHandlerTest {

    @Mock
    ChatMessageSender chatMessageSender;
    @Mock
    HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    TotalCommandHandler totalCommandHandler;

    @Test
    public void shouldSendHydrate() {
        totalCommandHandler.handle();

        verify(hydrateReminderPlugin,times(1)).getCurrentSessionHydrationBreaks();
        verify(chatMessageSender,times(1)).sendHydrateEmojiChatGameMessage(anyString());
    }
}
