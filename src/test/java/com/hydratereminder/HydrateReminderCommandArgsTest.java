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
        assertEquals("n", HydrateReminderCommandArgs.N.toString());
        assertEquals("p", HydrateReminderCommandArgs.P.toString());
        assertEquals("r", HydrateReminderCommandArgs.R.toString());
        assertEquals("h", HydrateReminderCommandArgs.H.toString());
        assertEquals("t", HydrateReminderCommandArgs.T.toString());
    }
}
