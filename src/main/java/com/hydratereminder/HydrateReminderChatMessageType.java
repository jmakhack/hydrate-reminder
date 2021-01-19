package com.hydratereminder;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Different types of chat messages that the Hydrate Reminder plugin
 * can use to send the reminder message
 * </p>
 * @author jmakhack
 */
@Getter
@AllArgsConstructor
public enum HydrateReminderChatMessageType
{
    GAMEMESSAGE("Game Message"),
    BROADCASTMESSAGE("Broadcast Message"),
    PUBLICCHAT("Public Chat"),
    CLANCHAT("Clan Chat");

    /**
     * Chat message type
     */
    private final String chatType;

    /**
     * <p>Get the chat message type as a String
     * </p>
     * @return chat message type
     * @since 1.0.0
     */
    @Override
    public String toString()
    {
        return getChatType();
    }
}
