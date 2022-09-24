package com.hydratereminder.command.chat;

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

    @InjectMocks
    private ChatMessageSender chatMessageSender;

    @Test
    public void shouldSendChatMessageWithoutEmojiWhenEmojiIsNotProvided() {
        //given
        ChatMessageType chatMessageType = ChatMessageType.WELCOME;
        String message = "Hello";
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.empty());

        //when
        chatMessageSender.sendHydrateEmojiChatMessage(chatMessageType, message);

        //then
        verify(client).addChatMessage(chatMessageType, "", "Hello", null);
    }

    @Test
    public void shouldSendChatMessageWithEmojiWhenEmojiIsProvided() {
        //given
        ChatMessageType chatMessageType = ChatMessageType.WELCOME;
        String message = "Hello";
        given(hydrateEmojiProvider.getHydrateEmojiId()).willReturn(Optional.of(10));

        //when
        chatMessageSender.sendHydrateEmojiChatMessage(chatMessageType, message);

        //then
        verify(client).addChatMessage(chatMessageType, "", "<img=10> Hello", null);
    }
}

