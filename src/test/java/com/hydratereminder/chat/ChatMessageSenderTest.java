package com.hydratereminder.chat;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the chat message sender logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class ChatMessageSenderTest
{

    /**
     *  Mock RuneLite client
     */
    @Mock
    private transient Client client;

    /**
     *  Mock hydrate emoji provider
     */
    @Mock
    private transient HydrateEmojiProvider hydrateEmojiProvider;

    /**
     *  Mock chat message type provider
     */
    @Mock
    private transient ChatMessageTypeProvider chatMessageTypeProvider;

    /**
     *  Mock chat message sender
     */
    @InjectMocks
    private transient ChatMessageSender chatMessageSender;

    /**
     *  Chat message to send in tests
     */
    private static final String CHAT_MESSAGE_TO_SEND = "Hello, World";

    /**
     * <p>Tests that chat message can be sent without providing an emoji
     * </p>
     */
    @Test
    void shouldSendChatGameMessageWithoutEmojiWhenEmojiIsNotProvided()
    {
        final ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.empty());
        chatMessageSender.sendHydrateEmojiChatGameMessage(CHAT_MESSAGE_TO_SEND);
        verify(client).addChatMessage(expectedChatMessageType, "", CHAT_MESSAGE_TO_SEND, null);
    }

    /**
     * <p>Tests that chat message can be sent with a provided emoji
     * </p>
     */
    @Test
    void shouldSendChatGameMessageWithEmojiWhenEmojiIsProvided()
    {
        final ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));
        chatMessageSender.sendHydrateEmojiChatGameMessage(CHAT_MESSAGE_TO_SEND);
        verify(client).addChatMessage(expectedChatMessageType, "",
                String.format("<img=10> %s", CHAT_MESSAGE_TO_SEND), null);
    }

    /**
     * <p>Tests that chat message can be specified to the broadcast type
     * </p>
     */
    @Test
    void shouldSendChatMessageWithBroadcastTypeWhenThatTypeWasProvided()
    {
        final ChatMessageType providedChatMessageType = ChatMessageType.BROADCAST;
        given(chatMessageTypeProvider.getChatNotificationMessageType()).willReturn(providedChatMessageType);
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));
        chatMessageSender.sendHydrateReminderChatMessage(CHAT_MESSAGE_TO_SEND);
        verify(client).addChatMessage(providedChatMessageType, "",
                String.format("<img=10> %s", CHAT_MESSAGE_TO_SEND), "");
    }
}
