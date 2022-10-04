package com.hydratereminder.dictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HydrateWelcomeMessageDictionaryTest {

    @Test
    public void shouldGetRandomWelcomeMessage(){

        final String welcomeMessage = HydrateWelcomeMessageDictionary.getRandomWelcomeMessage();

        assertNotNull(welcomeMessage);
        assertFalse(welcomeMessage.isEmpty());
    }
}
