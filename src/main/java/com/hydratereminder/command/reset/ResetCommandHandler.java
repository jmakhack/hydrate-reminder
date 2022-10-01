package com.hydratereminder.command.reset;

import com.hydratereminder.HydrateReminderPlugin;
import com.hydratereminder.chat.ChatMessageSender;
import java.time.Instant;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.hydratereminder.command.CommandHandler;

@Singleton
public class ResetCommandHandler implements CommandHandler {

    @Inject
    private HydrateReminderPlugin hydrateReminderPlugin;

    @Inject
    private ChatMessageSender chatMessageSender;

    /**
     * <p>Handle the hydrate reset command by resetting the current hydrate interval and displaying
     * a reset success message in chat
     * </p>
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
