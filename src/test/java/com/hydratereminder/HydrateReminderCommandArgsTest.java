package com.hydratereminder;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HydrateReminderCommandArgsTest
{
    @Test
    public void testCommandArgsToString()
    {
        assertEquals("next", HydrateReminderCommandArgs.NEXT.toString());
        assertEquals("prev", HydrateReminderCommandArgs.PREV.toString());
        assertEquals("reset", HydrateReminderCommandArgs.RESET.toString());
        assertEquals("help", HydrateReminderCommandArgs.HELP.toString());
        assertEquals("total", HydrateReminderCommandArgs.TOTAL.toString());
    }

    @Test
    public void testAliases()
    {
        assertEquals("next", HydrateReminderCommandArgs.NEXT.getCommandArg());
        assertEquals("prev", HydrateReminderCommandArgs.PREV.getCommandArg());
        assertEquals("reset", HydrateReminderCommandArgs.RESET.getCommandArg());
        assertEquals("help", HydrateReminderCommandArgs.HELP.getCommandArg());
        assertEquals("total", HydrateReminderCommandArgs.TOTAL.getCommandArg());

        assertEquals("n", HydrateReminderCommandArgs.NEXT.getCommandArgAbbr());
        assertEquals("p", HydrateReminderCommandArgs.PREV.getCommandArgAbbr());
        assertEquals("r", HydrateReminderCommandArgs.RESET.getCommandArgAbbr());
        assertEquals("h", HydrateReminderCommandArgs.HELP.getCommandArgAbbr());
        assertEquals("t", HydrateReminderCommandArgs.TOTAL.getCommandArgAbbr());
    }
}
