package com.hydratereminder.chat;

import com.hydratereminder.HydrateReminderChatMessageType;
import com.hydratereminder.HydrateReminderConfig;
import junit.framework.TestCase;
import net.runelite.api.ChatMessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ChatMessageTypeProviderTest extends TestCase {

    @Mock
    private HydrateReminderConfig config;

    @InjectMocks
    private ChatMessageTypeProvider chatMessageTypeProvider;

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

