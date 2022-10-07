package com.hydratereminder.command.next;

import java.time.Duration;
import java.time.Instant;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import com.hydratereminder.command.CommandHandler;

@Singleton
public class NextCommandHandler implements CommandHandler {

    private final transient ChatMessageSender chatMessageSender;
    private final transient HydrateReminderPlugin hydrateReminderPlugin;

    @Inject
    public NextCommandHandler(ChatMessageSender chatMessageSender, HydrateReminderPlugin hydrateReminderPlugin) {
        this.chatMessageSender = chatMessageSender;
        this.hydrateReminderPlugin = hydrateReminderPlugin;
    }

    /**
     * <p>Handle the hydrate next command by generating a chat message displaying the amount of time
     * until the next hydration break
     * </p>
     * @since 1.1.0
     */
    @Override
    public void handle() {
        final Instant nextHydrateReminderInstant = hydrateReminderPlugin.getNextHydrateReminderInstant();
        final Duration timeUntilNextBreak = Duration.between(Instant.now(), nextHydrateReminderInstant);
        final String timeString = hydrateReminderPlugin.getTimeDisplay(timeUntilNextBreak);
        final String message = timeString + " until the next hydration break.";
        chatMessageSender.sendHydrateEmojiChatGameMessage(message);
    }

}
