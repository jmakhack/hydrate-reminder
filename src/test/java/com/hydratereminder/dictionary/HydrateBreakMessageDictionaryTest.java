package com.hydratereminder.dictionary;

import com.hydratereminder.HydrateReminderPersonalityType;
import org.junit.Test;

import static com.hydratereminder.dictionary.HydrateBreakMessageDictionary.getRandomHydrateBreakMessageForPersonality;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HydrateBreakMessageDictionaryTest {

    @Test
    public void shouldChooseStraightforwardMessageWhenTheTypeIsStraightforward() {
        // given
        final HydrateReminderPersonalityType personalityType = HydrateReminderPersonalityType.STRAIGHTFORWARD;
        final String expectedMessage = "It's time for a quick hydration break";

        // when
        final String message = getRandomHydrateBreakMessageForPersonality(personalityType);

        // then
        assertEquals(expectedMessage, message);
    }

    @Test
    public void shouldChooseFunnyMessageWhenThePersonalityTypeIsFun() {
        // given
        final HydrateReminderPersonalityType personalityType = HydrateReminderPersonalityType.FUN;
        final String notExpectedMessage = "It's time for a quick hydration break";

        // when
        final String message = getRandomHydrateBreakMessageForPersonality(personalityType);

        // then
        assertNotEquals(notExpectedMessage, message);
    }

}
