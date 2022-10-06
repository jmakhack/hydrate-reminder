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
public class ChatMessageSenderTest {

    @Mock
    private Client client;

    @Mock
    private HydrateEmojiProvider hydrateEmojiProvider;

    @Mock
    private ChatMessageTypeProvider chatMessageTypeProvider;

    @InjectMocks
    private ChatMessageSender chatMessageSender;

    private String testMessage = "Hello";

    @Test
    public void shouldSendChatGameMessageWithoutEmojiWhenEmojiIsNotProvided() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.empty());

        //when
        chatMessageSender.sendHydrateEmojiChatGameMessage(testMessage);

        //then
        verify(client).addChatMessage(expectedChatMessageType, "", testMessage, null);
    }

    @Test
    public void shouldSendChatGameMessageWithEmojiWhenEmojiIsProvided() {
        //given
        ChatMessageType expectedChatMessageType = ChatMessageType.GAMEMESSAGE;
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateEmojiChatGameMessage(testMessage);

        //then
        verify(client).addChatMessage(expectedChatMessageType, "", "<img=10> " + testMessage, null);
    }

    @Test
    public void shouldSendChatMessageWithBroadcastTypeWhenThatTypeWasProvided() {
        //given
        ChatMessageType providedChatMessageType = ChatMessageType.BROADCAST;
        given(chatMessageTypeProvider.getChatNotificationMessageType()).willReturn(providedChatMessageType);
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateReminderChatMessage(testMessage);

        //then
        verify(client).addChatMessage(providedChatMessageType, "", "<img=10> " + testMessage, null);
    }
}

