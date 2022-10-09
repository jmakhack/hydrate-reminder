package com.hydratereminder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrateReminderChatMessageTypeTest {
    @Test
    void testChatMessageTypeToString() {
        assertEquals("Game Message", HydrateReminderChatMessageType.GAMEMESSAGE.toString());
        assertEquals("Broadcast Message", HydrateReminderChatMessageType.BROADCASTMESSAGE.toString());
        assertEquals("Public Chat", HydrateReminderChatMessageType.PUBLICCHAT.toString());
        assertEquals("Clan Chat", HydrateReminderChatMessageType.CLANCHAT.toString());
    }
}
