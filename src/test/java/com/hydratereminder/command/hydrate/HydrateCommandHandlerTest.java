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
class HydrateCommandHandlerTest {

    @Mock
    private transient ChatMessageSender chatMessageSender;
    @Mock
    private transient HydrateReminderPlugin hydrateReminderPlugin;
    @InjectMocks
    private transient HydrateCommandHandler hydrateCommandHandler;

    @Test
    void shouldResetCurrentHydrateIntervalAndIncreaseHydrationBreaksWhenHydrateCommandIsCalled() {
        //given and then
        hydrateCommandHandler.handle();

        //then
        verify(hydrateReminderPlugin).hydrateBetweenHydrationBreaks();
        verify(hydrateReminderPlugin).setResetState(true);
    }

    @Test
    void shouldSendProperMessageWhenHydrateCommandIsCalled() {
        //given
        String message = "Successfully hydrated before reminder interval finished";

        //when
        hydrateCommandHandler.handle();

        //then
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(message);
    }

}

