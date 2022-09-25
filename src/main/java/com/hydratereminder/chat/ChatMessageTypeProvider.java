package com.hydratereminder.chat;

import com.hydratereminder.HydrateReminderConfig;
import net.runelite.api.ChatMessageType;

import javax.inject.Inject;
import javax.inject.Singleton;

import static net.runelite.api.ChatMessageType.PUBLICCHAT;

@Singleton
public class ChatMessageTypeProvider {

    @Inject
    private HydrateReminderConfig config;

    /**
     * <p>Generates the type of chat message to send to the player
     * </p>
     *
     * @return the type of chat message to send to the player
     */
    protected ChatMessageType getChatNotificationMessageType() {
        ChatMessageType chatMessageType;
        switch (config.hydrateReminderChatMessageType()) {
            case BROADCASTMESSAGE:
                chatMessageType = ChatMessageType.BROADCAST;
                break;
            case PUBLICCHAT:
                chatMessageType = PUBLICCHAT;
                break;
            case CLANCHAT:
                chatMessageType = ChatMessageType.FRIENDSCHAT;
                break;
            default:
                chatMessageType = ChatMessageType.GAMEMESSAGE;
                break;
        }
        return chatMessageType;
    }
}

