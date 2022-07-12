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
        assertEquals("hydrate", HydrateReminderCommandArgs.HYDRATE.toString());
        assertEquals("help", HydrateReminderCommandArgs.HELP.toString());
        assertEquals("total", HydrateReminderCommandArgs.TOTAL.toString());
    }

    @Test
    public void testAliases()
    {
        assertEquals("next", HydrateReminderCommandArgs.NEXT.getCommandArg());
        assertEquals("prev", HydrateReminderCommandArgs.PREV.getCommandArg());
        assertEquals("reset", HydrateReminderCommandArgs.RESET.getCommandArg());
        assertEquals("hydrate", HydrateReminderCommandArgs.HYDRATE.getCommandArg());
        assertEquals("help", HydrateReminderCommandArgs.HELP.getCommandArg());
        assertEquals("total", HydrateReminderCommandArgs.TOTAL.getCommandArg());

        assertEquals("n", HydrateReminderCommandArgs.NEXT.getCommandArgAbbr());
        assertEquals("p", HydrateReminderCommandArgs.PREV.getCommandArgAbbr());
        assertEquals("r", HydrateReminderCommandArgs.RESET.getCommandArgAbbr());
        assertEquals("hr", HydrateReminderCommandArgs.HYDRATE.getCommandArgAbbr());
        assertEquals("h", HydrateReminderCommandArgs.HELP.getCommandArgAbbr());
        assertEquals("t", HydrateReminderCommandArgs.TOTAL.getCommandArgAbbr());
    }

    @Test
    public void testGetValue()
    {
        assertEquals(HydrateReminderCommandArgs.NEXT, HydrateReminderCommandArgs.getValue("next"));
        assertEquals(HydrateReminderCommandArgs.PREV, HydrateReminderCommandArgs.getValue("prev"));
        assertEquals(HydrateReminderCommandArgs.RESET, HydrateReminderCommandArgs.getValue("reset"));
        assertEquals(HydrateReminderCommandArgs.HYDRATE, HydrateReminderCommandArgs.getValue("hydrate"));
        assertEquals(HydrateReminderCommandArgs.HELP, HydrateReminderCommandArgs.getValue("help"));
        assertEquals(HydrateReminderCommandArgs.TOTAL, HydrateReminderCommandArgs.getValue("total"));

        assertEquals(HydrateReminderCommandArgs.NEXT, HydrateReminderCommandArgs.getValue("n"));
        assertEquals(HydrateReminderCommandArgs.PREV, HydrateReminderCommandArgs.getValue("p"));
        assertEquals(HydrateReminderCommandArgs.RESET, HydrateReminderCommandArgs.getValue("r"));
        assertEquals(HydrateReminderCommandArgs.HYDRATE, HydrateReminderCommandArgs.getValue("hr"));
        assertEquals(HydrateReminderCommandArgs.HELP, HydrateReminderCommandArgs.getValue("h"));
        assertEquals(HydrateReminderCommandArgs.TOTAL, HydrateReminderCommandArgs.getValue("t"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValueThrowsIfNullCommand()
    {
        HydrateReminderCommandArgs.getValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValueThrowsIfInvalidCommand()
    {
        HydrateReminderCommandArgs.getValue("dummy");
    }
}
