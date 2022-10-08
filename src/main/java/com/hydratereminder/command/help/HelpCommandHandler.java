package com.hydratereminder.command.help;

import  javax.inject.Inject;
import javax.inject.Singleton;

import com.hydratereminder.HydrateReminderCommandArgs;
import com.hydratereminder.chat.ChatMessageSender;
import com.hydratereminder.command.CommandHandler;

import static com.hydratereminder.Commons.HYDRATE_COMMAND_ALIAS;
import static com.hydratereminder.Commons.RUNELITE_COMMAND_PREFIX;

@Singleton
public class HelpCommandHandler implements CommandHandler {

    private final transient ChatMessageSender chatMessageSender;

    @Inject
    public HelpCommandHandler(ChatMessageSender chatMessageSender) {
        this.chatMessageSender = chatMessageSender;
    }

    /**
     * <p>Handle the hydrate help command by displaying all available command arguments
     * </p>
     * @since 1.1.0
     */
    @Override
    public void handle() {
        final StringBuilder commandList = new StringBuilder();
        final String listSeparator = ", ";
        for (HydrateReminderCommandArgs arg : HydrateReminderCommandArgs.values())
        {
            if (commandList.length() > 0)
            {
                commandList.append(listSeparator);
            }
            commandList.append(arg.toString());
        }
        final String helpString = String.format(
                "Available commands: %s%s %s",
                RUNELITE_COMMAND_PREFIX, HYDRATE_COMMAND_ALIAS, commandList
        );
        chatMessageSender.sendHydrateEmojiChatGameMessage(helpString);
    }

}
