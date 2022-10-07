package com.hydratereminder.chat;

import com.hydratereminder.HydrateReminderChatMessageType;
import com.hydratereminder.HydrateReminderConfig;
import net.runelite.api.ChatMessageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ChatMessageTypeProviderTest {

    @Mock
    private transient HydrateReminderConfig config;

    @InjectMocks
    private transient ChatMessageTypeProvider chatMessageTypeProvider;

    @Test
    public void shouldReturnGameMessageTypeWhenHydrateReminderChatMessageIsDifferentThanExpected() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(config.hydrateReminderChatMessageType()).willReturn(HydrateReminderChatMessageType.GAMEMESSAGE);

        //when
        ChatMessageType chatMessageType = chatMessageTypeProvider.getChatNotificationMessageType();

        //then
        assertEquals(expectedChatMessageType, chatMessageType);
    }

    @Test
    public void shouldReturnBroadCastMessageTypeWhenProvideBroadCastMessage() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.BROADCAST;
        given(config.hydrateReminderChatMessageType()).willReturn(HydrateReminderChatMessageType.BROADCASTMESSAGE);

        //when
        ChatMessageType chatMessageType = chatMessageTypeProvider.getChatNotificationMessageType();

        //then
        assertEquals(expectedChatMessageType, chatMessageType);
    }

}

