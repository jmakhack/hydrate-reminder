package com.hydratereminder;

import com.hydratereminder.command.NotRecognizedCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HydrateReminderCommandArgsTest {
    @ParameterizedTest
    @CsvSource({
            "next, NEXT",
            "prev, PREV",
            "reset, RESET",
            "hydrate, HYDRATE",
            "help, HELP",
            "total, TOTAL"
    })
    void testCommandArgsToString(final String expectedString, final HydrateReminderCommandArgs commandArg) {
        assertEquals(expectedString, commandArg.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "n, NEXT",
            "p, PREV",
            "r, RESET",
            "hr, HYDRATE",
            "h, HELP",
            "t, TOTAL"
    })
    void testCommandArgAbbrToString(final String expectedAbbr, final HydrateReminderCommandArgs commandArg) {
        assertEquals(expectedAbbr, commandArg.getCommandArgAbbr());
    }

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
    void testGetValue(final String expectedValue, final HydrateReminderCommandArgs commandArg) {
        assertEquals(HydrateReminderCommandArgs.getValue(expectedValue), commandArg);
    }

    @Test
    void testGetValueThrowsIfNullCommand() {
        assertThrows(
                NotRecognizedCommandException.class,
                () -> HydrateReminderCommandArgs.getValue(null)
        );
    }

    @Test
    void testGetValueThrowsIfInvalidCommand() {
        assertThrows(
                NotRecognizedCommandException.class,
                () -> HydrateReminderCommandArgs.getValue("dummy")
        );
    }
}
