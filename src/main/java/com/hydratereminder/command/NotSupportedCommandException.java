package com.hydratereminder.command;

import com.hydratereminder.HydrateReminderCommandArgs;

import static com.hydratereminder.Commons.HYDRATE_COMMAND_ALIAS;
import static com.hydratereminder.Commons.RUNELITE_COMMAND_PREFIX;

public class NotSupportedCommandException extends RuntimeException {
    private static final long serialVersionUID = 4328741L;

    public NotSupportedCommandException(HydrateReminderCommandArgs unrecognizedCommandName) {
        super(String.format(
                "%s%s %s is not a valid command",
                RUNELITE_COMMAND_PREFIX, HYDRATE_COMMAND_ALIAS, unrecognizedCommandName));
    }
}
