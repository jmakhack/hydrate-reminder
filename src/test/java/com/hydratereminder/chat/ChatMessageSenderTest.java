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

@ExtendWith(MockitoExtension.class)
class ChatMessageSenderTest {

    @Mock
    private transient Client client;

    @Mock
    private transient HydrateEmojiProvider hydrateEmojiProvider;

    @Mock
    private transient ChatMessageTypeProvider chatMessageTypeProvider;

    @InjectMocks
    private transient ChatMessageSender chatMessageSender;

    private static String  message = "Hello";

    @Test
    void shouldSendChatGameMessageWithoutEmojiWhenEmojiIsNotProvided() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.empty());

        //when
        chatMessageSender.sendHydrateEmojiChatGameMessage(message);

        //then
        verify(client).addChatMessage(expectedChatMessageType, "", message, null);
    }

    @Test
    void shouldSendChatGameMessageWithEmojiWhenEmojiIsProvided() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateEmojiChatGameMessage(message);

        //then
        verify(client).addChatMessage(expectedChatMessageType, "", String.format("<img=10> %s", message), null);
    }

    @Test
    void shouldSendChatMessageWithBroadcastTypeWhenThatTypeWasProvided() {
        //given
        ChatMessageType providedChatMessageType = ChatMessageType.BROADCAST;
        given(chatMessageTypeProvider.getChatNotificationMessageType()).willReturn(providedChatMessageType);
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateReminderChatMessage(message);

        //then
        verify(client).addChatMessage(providedChatMessageType, "", String.format("<img=10> %s", message), null);
    }
}

