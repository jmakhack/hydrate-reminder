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

@ExtendWith(MockitoExtension.class)
class HydrateBreakMessageDictionaryTest {

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "SIMPLE | It's time for a quick hydration break | 0",
            "FUN | Hydration time is now | 10",
            "CARING | Don't be thirsty. Drink water | 5",
            "NERDY | Water has a high specific heat capacity, have some water to regulate that body temperature of yours | 3",
            "ROMANTIC | Every time I think about you, my heart dances. Drink some water to keep it dancing. | 8",
            "POLITE | Your beautiful skin will shine brighter when hydrated. Please go ahead | 1",
            "PIRATE | Arrr, the ocean reminds me, that you should take a water break! | 6",
            "MOTIVATIONAL | Thousands Have Lived Without Love, Not One Without Water. Take a sip | 13",
            "AGGRESSIVE | You gotta drink some Water, Yeah! | 9",
            "SANTA | All I want for Christmas is you....drinking enough water | 1",
            "KAWAII | Sumimasen! you forgot to drink water | 9"
    })
    void shouldChooseCorrectMessageBasedOnPersonalityType(
            HydrateReminderPersonalityType personalityType, String expectedMessage, int value) {
        try (MockedConstruction<SecureRandom> ignored = Mockito.mockConstruction(SecureRandom.class, (mock, context) ->
                Mockito.when(mock.nextInt(Mockito.anyInt())).thenReturn(value)
        )) {
            String givenMessage = getRandomHydrateBreakMessageForPersonality(personalityType);

            assertEquals(givenMessage, expectedMessage);
        }
    }
}
