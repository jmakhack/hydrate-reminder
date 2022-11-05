package com.hydratereminder.dictionary;

import com.hydratereminder.HydrateReminderPersonalityType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;

import static com.hydratereminder.dictionary.HydrateBreakMessageDictionary.getRandomHydrateBreakMessageForPersonality;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>The unit tests for the hydrate break message dictionary logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class HydrateBreakMessageDictionaryTest
{

    /**
     * <p>Tests that the expected message is generated for a variety of
     * personality types given a specified random generated value
     * </p>
     * @param personalityType hydrate reminder personality type
     * @param expectedMessage expected generated notification message
     * @param value random generator value
     */
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "SIMPLE | It's time for a quick hydration break | 0",
            "FUN | Hydration time is now | 10",
            "CARING | Don't be thirsty. Drink water | 5",
            "NERDY | Water has a high specific heat capacity, " +
                    "have some water to regulate that body temperature of yours | 3",
            "ROMANTIC | Every time I think about you, my heart dances. Drink some water to keep it dancing. | 8",
            "POLITE | Your beautiful skin will shine brighter when hydrated. Please go ahead | 1",
            "PIRATE | Arrr, the ocean reminds me, that you should take a water break! | 6",
            "MOTIVATIONAL | Thousands Have Lived Without Love, Not One Without Water. Take a sip | 13",
            "AGGRESSIVE | You gotta drink some Water, Yeah! | 9",
            "SANTA | All I want for Christmas is you....drinking enough water | 1",
            "KAWAII | Sumimasen! you forgot to drink water | 9",
            "HYPE | The sun believes you'll be dehydrated soon , Go prove it wrong!! | 2"
    })
    /* default */ void shouldChooseCorrectMessageBasedOnPersonalityType(
            final HydrateReminderPersonalityType personalityType, final String expectedMessage, final int value)
    {
        try (MockedConstruction<SecureRandom> ignored = Mockito.mockConstruction(SecureRandom.class,
                (mock, context) -> Mockito.when(mock.nextInt(Mockito.anyInt())).thenReturn(value)
        ))
        {
            final String givenMessage = getRandomHydrateBreakMessageForPersonality(personalityType);
            assertEquals(givenMessage, expectedMessage, "Unexpected message received for personality type");
        }
    }
}
