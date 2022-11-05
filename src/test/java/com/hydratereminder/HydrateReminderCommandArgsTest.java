package com.hydratereminder;

import com.hydratereminder.command.NotRecognizedCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <p>The unit tests for the hydrate reminder command args logic
 * </p>
 */
class HydrateReminderCommandArgsTest
{

    /**
     * <p>Tests that the correct string is returned for a variety
     * of command arguments
     * </p>
     * @param expectedString expected string value for command
     * @param commandArg command argument to get string value of
     */
    @ParameterizedTest
    @CsvSource({
            "next, NEXT",
            "prev, PREV",
            "reset, RESET",
            "hydrate, HYDRATE",
            "help, HELP",
            "total, TOTAL"
    })
    /* default */ void testCommandArgsToString(final String expectedString, final HydrateReminderCommandArgs commandArg)
    {
        assertEquals(expectedString, commandArg.toString(), "Unexpected command arg string received");
    }

    /**
     * <p>Tests that the correct alias is returned for a variety
     * of command arguments
     * </p>
     * @param expectedAbbr expected string value for command alias
     * @param commandArg command argument to get string value of
     */
    @ParameterizedTest
    @CsvSource({
            "n, NEXT",
            "p, PREV",
            "r, RESET",
            "hr, HYDRATE",
            "h, HELP",
            "t, TOTAL"
    })
    /* default */ void testCommandArgAbbrToString(final String expectedAbbr, final HydrateReminderCommandArgs commandArg)
    {
        assertEquals(expectedAbbr, commandArg.getCommandArgAbbr(),
                "Unexpected command arg alias string received");
    }

    /**
     * <p>Tests that the correct command argument is returned for
     * a variety of values
     * </p>
     * @param value value to get command argument of
     * @param expectedCommandArg expected command argument
     */
    @ParameterizedTest
    @CsvSource({
            "next, NEXT",
            "prev, PREV",
            "reset, RESET",
            "hydrate, HYDRATE",
            "help, HELP",
            "total, TOTAL",
            "n, NEXT",
            "p, PREV",
            "r, RESET",
            "hr, HYDRATE",
            "h, HELP",
            "t, TOTAL"
    })
    /* default */ void testGetValue(final String value, final HydrateReminderCommandArgs expectedCommandArg)
    {
        assertEquals(HydrateReminderCommandArgs.getValue(value), expectedCommandArg,
                "Unexpected command arg value received");
    }

    /**
     * <p>Tests that the proper exception is thrown when the command
     * argument is null
     * </p>
     */
    @Test
    /* default */ void testGetValueThrowsIfNullCommand()
    {
        assertThrows(
                NotRecognizedCommandException.class,
                () -> HydrateReminderCommandArgs.getValue(null),
                "Expected NotRecognizedCommandException to be thrown"
        );
    }

    /**
     * <p>Tests that the proper exception is thrown when the command
     * argument is invalid
     * </p>
     */
    @Test
    /* default */ void testGetValueThrowsIfInvalidCommand()
    {
        assertThrows(
                NotRecognizedCommandException.class,
                () -> HydrateReminderCommandArgs.getValue("dummy"),
                "Expected NotRecognizedCommandException to be thrown"
        );
    }
}
