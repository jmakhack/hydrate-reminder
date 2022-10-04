package com.hydratereminder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HydrateReminderChatMessageTypeTest
{
    @Test
    public void testChatMessageTypeToString()
    {
        assertEquals("Game Message", HydrateReminderChatMessageType.GAMEMESSAGE.toString());
        assertEquals("Broadcast Message", HydrateReminderChatMessageType.BROADCASTMESSAGE.toString());
        assertEquals("Public Chat", HydrateReminderChatMessageType.PUBLICCHAT.toString());
        assertEquals("Clan Chat", HydrateReminderChatMessageType.CLANCHAT.toString());
    }
}
