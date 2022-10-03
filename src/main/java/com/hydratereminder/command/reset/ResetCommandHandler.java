package com.hydratereminder.command.reset;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import com.hydratereminder.command.CommandHandler;
import java.time.Instant;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResetCommandHandler implements CommandHandler {

    private final HydrateReminderPlugin hydrateReminderPlugin;

    private final ChatMessageSender chatMessageSender;

    @Inject
    public ResetCommandHandler(HydrateReminderPlugin hydrateReminderPlugin, ChatMessageSender chatMessageSender) {
        this.hydrateReminderPlugin = hydrateReminderPlugin;
        this.chatMessageSender = chatMessageSender;
    }

    /**
     * <p>Handle the hydrate reset command by resetting the current hydrate interval and displaying
     * a reset success message in chat
     * </p>
     *
     * @since 1.1.0
     */
    @Override
    public void handle() {
        hydrateReminderPlugin.setLastHydrateInstant(Optional.of(Instant.now()));
        hydrateReminderPlugin.setResetState(true);
        final String resetString = "Hydrate reminder interval has been successfully reset.";
        chatMessageSender.sendHydrateEmojiChatGameMessage(resetString);
    }

}
