package com.hydratereminder.command.hydrate;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import com.hydratereminder.command.CommandHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HydrateCommandHandler implements CommandHandler {

    private final transient ChatMessageSender chatMessageSender;
    private final transient HydrateReminderPlugin hydrateReminderPlugin;

    @Inject
    public HydrateCommandHandler(ChatMessageSender chatMessageSender, HydrateReminderPlugin hydrateReminderPlugin) {
        this.chatMessageSender = chatMessageSender;
        this.hydrateReminderPlugin = hydrateReminderPlugin;
    }

    /**
     * <p>Handle the hydrate "hydrate" command by resetting the current hydrate interval, increasing
     * hydration breaks taken during the session and displaying a hydration success message in chat
     * </p>
     */
    @Override
    public void handle() {
        hydrateReminderPlugin.hydrateBetweenHydrationBreaks();
        hydrateReminderPlugin.setResetState(true);
        final String hydratedString = "Successfully hydrated before reminder interval finished";
        chatMessageSender.sendHydrateEmojiChatGameMessage(hydratedString);
    }

}
