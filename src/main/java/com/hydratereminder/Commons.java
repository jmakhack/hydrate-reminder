package com.hydratereminder;

import java.io.File;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

public class Commons {
    /**
     * Main command name for the Hydrate Reminder plugin
     */
    public static final String HYDRATE_COMMAND_NAME = "hydrate";
    public static final String HYDRATE_COMMAND_ALIAS = "hr";

    /**
     * <p>Directory for Hydrate Reminder</p>
     */
    public static final File HYDRATION_REMINDER_DIR = new File(RUNELITE_DIR, "hydrateReminder");

    /**
     * <p>File for total hydration breaks</p>
     */
    public static final File HYDRATION_REMINDER_BREAKS_FILE =
            new File(HYDRATION_REMINDER_DIR, "hydrateReminderStats.json");

    /**
     * Prefix for all chat commands in RuneLite
     */
    public static final String RUNELITE_COMMAND_PREFIX = "::";
}
