package com.hydratereminder.dictionary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class HydrateWelcomeMessageDictionaryTest {

    @Test
    public void shouldGetRandomWelcomeMessage(){

        final String welcomeMessage = HydrateWelcomeMessageDictionary.getRandomWelcomeMessage();

        assertNotNull(welcomeMessage);
        assertFalse(welcomeMessage.isEmpty());
    }
}
