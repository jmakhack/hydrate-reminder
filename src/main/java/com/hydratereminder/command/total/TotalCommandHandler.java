package com.hydratereminder.command.total;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.hydratereminder.command.CommandHandler;

@Singleton
public class TotalCommandHandler implements CommandHandler {

    private final transient ChatMessageSender chatMessageSender;

    private final transient HydrateReminderPlugin hydrateReminderPlugin;

    @Inject
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
        final int numCurrentBreaks = hydrateReminderPlugin.getCurrentSessionHydrationBreaks();
        final int numAllTimeBreaks = hydrateReminderPlugin.getAllTimeHydrationBreaks() + numCurrentBreaks;
        final String currentTotalString = String.format("Current session: %d hydration %s.",
                numCurrentBreaks, getBreakText(numCurrentBreaks));
        final String totalBreakString = String.format("All time: %d hydration %s.",
                numAllTimeBreaks, getBreakText(numAllTimeBreaks));
        chatMessageSender.sendHydrateEmojiChatGameMessage(currentTotalString);
        chatMessageSender.sendHydrateEmojiChatGameMessage(totalBreakString);
    }

    /**
     * <p>Return 'break' if number of breaks is 1 and 'breaks' otherwise</p>
     * @param numBreaks number of hydration breaks
     * @return
     */
    private String getBreakText(int numBreaks) {
        return numBreaks == 1 ? "break" : "breaks";
    }
}
