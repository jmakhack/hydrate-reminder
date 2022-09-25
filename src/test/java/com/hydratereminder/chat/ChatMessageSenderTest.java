package com.hydratereminder.chat;

import junit.framework.TestCase;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChatMessageSenderTest extends TestCase {

    @Mock
    private Client client;

    @Mock
    private HydrateEmojiProvider hydrateEmojiProvider;

    @Mock
    private ChatMessageTypeProvider chatMessageTypeProvider;

    @InjectMocks
    private ChatMessageSender chatMessageSender;

    @Test
    public void shouldSendChatGameMessageWithoutEmojiWhenEmojiIsNotProvided() {
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
    public void shouldSendChatGameMessageWithEmojiWhenEmojiIsProvided() {
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
    public void shouldSendChatMessageWithBroadcastTypeWhenThatTypeWasProvided() {
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

