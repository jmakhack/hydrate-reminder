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

    @Test
    void shouldSendChatGameMessageWithoutEmojiWhenEmojiIsNotProvided() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        String message = "Hello";
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.empty());

        //when
        chatMessageSender.sendHydrateEmojiChatGameMessage(message);

        //then
        verify(client).addChatMessage(expectedChatMessageType, "", "Hello", null);
    }

    @Test
    void shouldSendChatGameMessageWithEmojiWhenEmojiIsProvided() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        String message = "Hello";
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateEmojiChatGameMessage(message);

        //then
        verify(client).addChatMessage(expectedChatMessageType, "", "<img=10> Hello", null);
    }

    @Test
    void shouldSendChatMessageWithBroadcastTypeWhenThatTypeWasProvided() {
        //given
        ChatMessageType providedChatMessageType = ChatMessageType.BROADCAST;
        String message = "Hello";
        given(chatMessageTypeProvider.getChatNotificationMessageType()).willReturn(providedChatMessageType);
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateReminderChatMessage(message);

        //then
        verify(client).addChatMessage(providedChatMessageType, "", "<img=10> Hello", null);
    }
}

