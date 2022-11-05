package com.hydratereminder.chat;

import net.runelite.api.Client;
import net.runelite.api.IndexedSprite;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * <p>The unit tests for the hydrate emoji provider logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class HydrateEmojiProviderTest
{

    /**
     * Mock RuneLite client
     */
    @Mock
    private transient Client client;

    /**
     * Mock hydrate emoji provider
     */
    @InjectMocks
    private transient HydrateEmojiProvider hydrateEmojiProvider;

    /**
     * <p>Tests that the hydrate emoji id is set to 3 when the hydrate
     * emoji is loaded and has 2 elements
     * </p>
     */
    @Test
    /* default */ void shouldSetHydrateEmojiIdTo3WhenHydrateEmojiIsLoadedAndHave2Elements()
    {
        final IndexedSprite indexedSprite = Mockito.mock(IndexedSprite.class);
        final IndexedSprite indexedSpriteToAdd = Mockito.mock(IndexedSprite.class);
        final IndexedSprite[] indexedSprites = {indexedSprite, indexedSprite, indexedSprite};
        given(client.getModIcons()).willReturn(indexedSprites);
        given(client.createIndexedSprite()).willReturn(indexedSpriteToAdd);
        final IndexedSprite[] expectedOutput = {indexedSprite, indexedSprite, indexedSprite, indexedSpriteToAdd};
        hydrateEmojiProvider.loadHydrateEmoji();
        final int hydrateEmojiId = hydrateEmojiProvider.getHydrateEmojiId().get();
        assertEquals(3, hydrateEmojiId, "Expected hydrate emoji id to be 3");
        verify(client).setModIcons(expectedOutput);
    }
}
