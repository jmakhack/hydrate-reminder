package com.hydratereminder;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>All command arguments that the Hydrate Reminder plugin supports
 * </p>
 * @author jmakhack
 */
@Getter
@AllArgsConstructor
public enum HydrateReminderCommandArgs
{
    NEXT("next"),
    PREV("prev"),
    RESET("reset"),
    HELP("help");

    /**
     * Command argument name
     */
    private final String commandArg;

    /**
     * <p>Get the command argument name as a String
     * </p>
     * @return command argument name
     * @since 1.1.0
     */
    @Override
    public String toString()
    {
        return getCommandArg();
    }
}
