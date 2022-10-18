package com.hydratereminder.command;

import static com.hydratereminder.Commons.HYDRATE_COMMAND_ALIAS;
import static com.hydratereminder.Commons.RUNELITE_COMMAND_PREFIX;

public class NotRecognizedCommandException extends RuntimeException {
    private static final long serialVersionUID = 4328743L;

    public NotRecognizedCommandException(String unsupportedCommandName) {
        super(String.format(
                "%s%s %s is not supported command",
                RUNELITE_COMMAND_PREFIX, HYDRATE_COMMAND_ALIAS, unsupportedCommandName
        ));
    }
}
