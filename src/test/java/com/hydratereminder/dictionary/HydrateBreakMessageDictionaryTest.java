package com.hydratereminder.dictionary;

import com.hydratereminder.HydrateReminderPersonalityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.hydratereminder.dictionary.HydrateBreakMessageDictionary.getRandomHydrateBreakMessageForPersonality;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class HydrateBreakMessageDictionaryTest {

    @Test
    public void shouldChooseSimpleMessageWhenTheTypeIsSimple() {
        // given
        final HydrateReminderPersonalityType personalityType = HydrateReminderPersonalityType.SIMPLE;
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

    @Test
    public void shouldChooseCaringMessageWhenThePersonalityTypeIsCaring() {

    }

}
