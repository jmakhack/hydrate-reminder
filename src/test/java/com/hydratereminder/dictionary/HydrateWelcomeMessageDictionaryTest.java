package com.hydratereminder.dictionary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>The unit tests for the hydrate welcome message dictionary logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class HydrateWelcomeMessageDictionaryTest
{

    /**
     * <p>Tests that welcome message is generated based on random value
     * </p>
     */
    @Test
    void shouldGetRandomWelcomeMessage()
    {
        try (MockedConstruction<SecureRandom> mockRandom = Mockito.mockConstruction(SecureRandom.class,
                (mock, context) -> Mockito.when(mock.nextInt(Mockito.anyInt())).thenReturn(4)
        ))
        {
            final String givenMessage = HydrateWelcomeMessageDictionary.getRandomWelcomeMessage();
            final String expectedMessage = "Cheers to staying hydrated!";
            assertEquals(givenMessage, expectedMessage, "Unexpected welcome message received");
        }
    }
}
