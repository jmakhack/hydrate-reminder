package com.hydratereminder.command.total;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import javax.inject.Singleton;

import com.hydratereminder.command.CommandHandler;

@Singleton
public class TotalCommandHandler implements CommandHandler {

    private final ChatMessageSender chatMessageSender;

    private final HydrateReminderPlugin hydrateReminderPlugin;

    public TotalCommandHandler(ChatMessageSender chatMessageSender, HydrateReminderPlugin hydrateReminderPlugin) {
        this.chatMessageSender = chatMessageSender;
        this.hydrateReminderPlugin = hydrateReminderPlugin;
    }

    /**
     * <p>Handle the hydrate total command by displaying the overall number of hydration breaks taken
     * </p>
     * @since 1.2.0
     */
    @Override
    public void handle() {
        // TODO: Output the overall total number of hydration breaks across sessions
        final int numBreaks = hydrateReminderPlugin.getCurrentSessionHydrationBreaks();
        final String breakText = numBreaks == 1 ? "break" : "breaks";
        final String totalString = String.format("Current session: %d hydration %s.",
                numBreaks, breakText);
        chatMessageSender.sendHydrateEmojiChatGameMessage(totalString);
    }

}
