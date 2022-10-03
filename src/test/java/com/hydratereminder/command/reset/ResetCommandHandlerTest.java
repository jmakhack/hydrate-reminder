package com.hydratereminder.command.reset;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResetCommandHandlerTest {

    @Mock
    private ChatMessageSender chatMessageSender;
    @Mock
    private HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    private ResetCommandHandler resetCommandHandler;

    @Test
    public void shouldResetHydrate() {
        resetCommandHandler.handle();

        final String resetString = "Hydrate reminder interval has been successfully reset.";

        verify(hydrateReminderPlugin, times(1)).setLastHydrateInstant(any());
        verify(hydrateReminderPlugin, times(1)).setResetState(true);
        verify(chatMessageSender, times(1)).sendHydrateEmojiChatGameMessage(resetString);

    }

}
