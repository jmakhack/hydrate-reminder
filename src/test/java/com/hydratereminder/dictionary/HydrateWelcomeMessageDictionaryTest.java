package com.hydratereminder.dictionary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.security.SecureRandom;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SecureRandom.class, HydrateWelcomeMessageDictionary.class })
public class HydrateWelcomeMessageDictionaryTest {

    @Test
    public void shouldGetRandomWelcomeMessage() throws Exception {
        SecureRandom mockRandom = Mockito.mock(SecureRandom.class);
        PowerMockito.whenNew(SecureRandom.class).withNoArguments().thenReturn(mockRandom);
        Mockito.when(mockRandom.nextInt(Mockito.anyInt())).thenReturn(4);

        String givenMessage = HydrateWelcomeMessageDictionary.getRandomWelcomeMessage();
        String expectedMessage = "Cheers to staying hydrated!";

        assertEquals(givenMessage, expectedMessage);
    }
}
