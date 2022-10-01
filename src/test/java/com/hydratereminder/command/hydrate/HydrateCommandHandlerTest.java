package com.hydratereminder.command.hydrate;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HydrateCommandHandlerTest {

    @Mock
    private ChatMessageSender chatMessageSender;
    @Mock
    private HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    private HydrateCommandHandler hydrateCommandHandler;

    @Test
    public void shouldResetCurrentHydrateIntervalAndIncreaseHydrationBreaksWhenHydrateCommandIsCalled() {
        //given and then
        hydrateCommandHandler.handle();

        //then
        verify(hydrateReminderPlugin).hydrateBetweenHydrationBreaks();
        verify(hydrateReminderPlugin).setResetState(true);
    }

    @Test
    public void shouldSendProperMessageWhenHydrateCommandIsCalled() {
        //given
        String message = "Successfully hydrated before reminder interval finished";

        //when
        hydrateCommandHandler.handle();

        //then
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(message);
    }

}

