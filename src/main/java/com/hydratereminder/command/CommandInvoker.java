package com.hydratereminder.command;

import java.util.Locale;

import com.hydratereminder.HydrateReminderCommandArgs;
import com.hydratereminder.chat.ChatMessageSender;
import net.runelite.api.events.CommandExecuted;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.hydratereminder.Commons.HYDRATE_COMMAND_ALIAS;
import static com.hydratereminder.Commons.HYDRATE_COMMAND_NAME;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

@Singleton
public class CommandInvoker {

    private final CommandCreator commandCreator;
    private final ChatMessageSender chatMessageSender;

    @Inject
    public CommandInvoker(CommandCreator commandCreator, ChatMessageSender chatMessageSender) {
        this.commandCreator = commandCreator;
        this.chatMessageSender = chatMessageSender;
    }

    public void invokeCommand(CommandExecuted commandExecuted) {
        if (!isHydrateReminderCommand(commandExecuted)) {
            return;
        }
        final String[] commandArguments = commandExecuted.getArguments();
        if (isNotEmpty(commandArguments)) {
            final String rawCommand = commandArguments[0].toLowerCase(Locale.getDefault());
            try {
                final HydrateReminderCommandArgs commandType = getCommandType(rawCommand);
                final Command command = commandCreator.createFrom(commandType);
                command.execute();
            } catch (NotSupportedCommandException exception) {
                printHelpMessage(exception.getReason());
            } catch (NotRecognizedCommandException exception) {
                printHelpMessage(exception.getReason());
            }
        }
    }

    private void printHelpMessage(String problemReason) {
        chatMessageSender.sendHydrateEmojiChatGameMessage(problemReason);
        final Command helpCommand = commandCreator.createFrom(HydrateReminderCommandArgs.HELP);
        helpCommand.execute();
    }

    private boolean isHydrateReminderCommand(CommandExecuted commandExecuted) {
        final String command = commandExecuted.getCommand();
        return command.equalsIgnoreCase(HYDRATE_COMMAND_NAME) || command.equalsIgnoreCase(HYDRATE_COMMAND_ALIAS);
    }

    private static HydrateReminderCommandArgs getCommandType(String rawCommand) {
        return HydrateReminderCommandArgs.getValue(rawCommand);
    }

}
