package com.hydratereminder;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
