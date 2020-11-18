package com.hydratereminder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HydrateReminderChatMessageType
{
    GAMEMESSAGE("Game Message"),
    BROADCASTMESSAGE("Broadcast Message"),
    PUBLICCHAT("Public Chat"),
    CLANCHAT("Clan Chat");

    private final String chatType;

    @Override
    public String toString()
    {
        return getChatType();
    }
}
