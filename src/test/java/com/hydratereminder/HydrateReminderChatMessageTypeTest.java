package com.hydratereminder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrateReminderChatMessageTypeTest {
    @ParameterizedTest
    @CsvSource({
            "Game Message, GAMEMESSAGE",
            "Broadcast Message, BROADCASTMESSAGE",
            "Public Chat, PUBLICCHAT",
            "Clan Chat, CLANCHAT"
    })
    void testChatMessageTypeToString(final String expectedString, final HydrateReminderChatMessageType chatMessageType) {
        assertEquals(expectedString, chatMessageType.toString());
    }
}
