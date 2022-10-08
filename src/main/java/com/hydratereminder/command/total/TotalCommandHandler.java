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
        final int numTotalBreaks = hydrateReminderPlugin.getTotalSessionHydrationBreaks();
        final String currentBreakText = numCurrentBreaks == 1 ? "break" : "breaks";
        final String totalBreakText = numTotalBreaks == 1 ? "break" : "breaks";
        final String currentTotalString = String.format("Current session: %d hydration %s.",
                numCurrentBreaks, currentBreakText);
        final String totalBreakString = String.format("All time: %d hydration %s.", numTotalBreaks, totalBreakText);
        chatMessageSender.sendHydrateEmojiChatGameMessage(currentTotalString);
        chatMessageSender.sendHydrateEmojiChatGameMessage(totalBreakString);
    }
}
