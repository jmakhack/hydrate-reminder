package com.hydratereminder.dictionary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HydrateWelcomeMessageDictionaryTest {

    @Test
    public void shouldGetRandomWelcomeMessage() {
        try (MockedConstruction<SecureRandom> mockRandom = Mockito.mockConstruction(SecureRandom.class, (mock, context) -> {
            Mockito.when(mock.nextInt(Mockito.anyInt())).thenReturn(4);
        })) {
            String givenMessage = HydrateWelcomeMessageDictionary.getRandomWelcomeMessage();
            String expectedMessage = "Cheers to staying hydrated!";

            assertEquals(givenMessage, expectedMessage);
        }
    }
}

