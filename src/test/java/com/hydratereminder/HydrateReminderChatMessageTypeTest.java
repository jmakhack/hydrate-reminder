package com.hydratereminder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>The unit tests for the hydrate reminder chat message type logic
 * </p>
 */
class HydrateReminderChatMessageTypeTest
{

    /**
     * <p>Tests that a variety of chat message types returns the proper
     * display string
     * </p>
     * @param expectedString expected string value for chat message type
     * @param chatMessageType chat message type to get string value of
     */
    @ParameterizedTest
    @CsvSource({
            "Game Message, GAMEMESSAGE",
            "Broadcast Message, BROADCASTMESSAGE",
            "Public Chat, PUBLICCHAT",
            "Clan Chat, CLANCHAT"
    })
    /* default */ void testChatMessageTypeToString(final String expectedString,
                                     final HydrateReminderChatMessageType chatMessageType)
    {
        assertEquals(expectedString, chatMessageType.toString(),
                "Unexpected chat message type string value received");
    }
}
