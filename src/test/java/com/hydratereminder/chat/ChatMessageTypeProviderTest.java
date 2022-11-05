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

/**
 * <p>The unit tests for the chat message type provider logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class ChatMessageTypeProviderTest
{

    /**
     * Mock chat message sender
     */
    @Mock
    private transient HydrateReminderConfig config;

    /**
     * Mock chat message type provider
     */
    @InjectMocks
    private transient ChatMessageTypeProvider chatMessageTypeProvider;

    /**
     * <p>Tests that the chat message type provider defaults to returning the
     * game message type when unknown chat message type is provided
     * </p>
     */
    @Test
    /* default */ void shouldReturnGameMessageTypeWhenHydrateReminderChatMessageIsDifferentThanExpected()
    {
        final ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(config.hydrateReminderChatMessageType()).willReturn(HydrateReminderChatMessageType.GAMEMESSAGE);
        final ChatMessageType chatMessageType = chatMessageTypeProvider.getChatNotificationMessageType();
        assertEquals(expectedChatMessageType, chatMessageType, "Expected chat message type to be GAMEMESSAGE");
    }

    /**
     * <p>Tests that the broadcast chat message type is returned when the
     * broadcast chat message type is provided
     * </p>
     */
    @Test
    /* default */ void shouldReturnBroadCastMessageTypeWhenProvideBroadCastMessage()
    {
        final ChatMessageType expectedChatMessageType = ChatMessageType.BROADCAST;
        given(config.hydrateReminderChatMessageType()).willReturn(HydrateReminderChatMessageType.BROADCASTMESSAGE);
        final ChatMessageType chatMessageType = chatMessageTypeProvider.getChatNotificationMessageType();
        assertEquals(expectedChatMessageType, chatMessageType, "Expected chat message type to be BROADCAST");
    }
}
