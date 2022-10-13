package com.hydratereminder.chat;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.hydratereminder.dictionary.HydrateWelcomeMessageDictionary.getRandomWelcomeMessage;

@Slf4j
@Singleton
public class ChatMessageSender {

    @Inject
    private transient Client client;

    @Inject
    private transient ChatMessageTypeProvider chatMessageTypeProvider;

    @Inject
    private transient HydrateEmojiProvider hydrateEmojiProvider;

    /**
     * <p>Generates and sends a neatly formatted chat GAME MESSAGE prefixed by the
     * hydrate emoji to the player
     * </p>
     *
     * @param message the hydrate reminder message to display to the player
     */
    public void sendHydrateEmojiChatGameMessage(String message) {
        if (!hydrateEmojiProvider.getHydrateEmojiId().isPresent()) {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message, null);
            return;
        }
        final String hydrateEmoji = String.format("<img=%d>", hydrateEmojiProvider.getHydrateEmojiId().get());
        final String hydrateMessage = hydrateEmoji + " " + message;
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", hydrateMessage, null);
        log.debug("Successfully sent chat message");
    }

    /**
     * <p>Sends a hydrate reminder message to the player via the ingame chat box
     * </p>
     *
     * @param message the hydrate reminder message to display to the player
     */
    public void sendHydrateReminderChatMessage(String message) {
        final ChatMessageType chatMessageType = chatMessageTypeProvider.getChatNotificationMessageType();
        sendHydrateEmojiChatMessage(chatMessageType, message);
        if (log.isDebugEnabled()) {
            log.debug(String.format("Successfully sent chat notification of type: %s", chatMessageType.toString()));
        }
    }

    /**
     * <p>Sends a random hydrate welcome message in chat
     * </p>
     */
    public void sendHydrateWelcomeChatMessage() {
        final String hydrateWelcomeMessage = getRandomWelcomeMessage();
        sendHydrateEmojiChatGameMessage(hydrateWelcomeMessage);
    }

    /**
     * <p>Generates and sends a neatly formatted SPECIFIC TYPE chat message with prefixed by the
     * hydrate emoji to the player
     * </p>
     *
     * @param type    the type of chat message to send
     * @param message the hydrate reminder message to display to the player
     */
    private void sendHydrateEmojiChatMessage(ChatMessageType type, String message) {
        if (!hydrateEmojiProvider.getHydrateEmojiId().isPresent()) {
            client.addChatMessage(type, "", message, null);
            return;
        }
        final String hydrateEmoji = String.format("<img=%d>", hydrateEmojiProvider.getHydrateEmojiId().get());
        final StringBuilder hydrateMessage = new StringBuilder();
        String sender = hydrateEmoji;
        if (type != ChatMessageType.FRIENDSCHAT) {
            hydrateMessage.append(hydrateEmoji);
            hydrateMessage.append(' ');
            sender = "";
        }
        hydrateMessage.append(message);
        client.addChatMessage(type, "", hydrateMessage.toString(), sender);
        log.debug("Successfully sent chat message");
    }
}

