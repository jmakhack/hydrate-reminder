package com.hydratereminder.command.reset;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResetCommandHandlerTest {

    @Mock
    ChatMessageSender chatMessageSender;
    @Mock
    HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    ResetCommandHandler resetCommandHandler;

    @Test
    public void shouldResetHydrate() {

        resetCommandHandler.handle();

        verify(hydrateReminderPlugin, times(1)).setLastHydrateInstant(any());
        verify(hydrateReminderPlugin, times(1)).setResetState(anyBoolean());
        verify(chatMessageSender, times(1)).sendHydrateEmojiChatGameMessage(anyString());

    }

}