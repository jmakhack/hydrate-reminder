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

@ExtendWith(MockitoExtension.class)
class HydrateEmojiProviderTest {

    @Mock
    private transient Client client;

    @InjectMocks
    private transient HydrateEmojiProvider hydrateEmojiProvider;


    @Test
    void shouldSetHydrateEmojiIdTo3WhenHydrateEmojiIsLoadedAndHave2Elements() {
        //given
        IndexedSprite indexedSprite = Mockito.mock(IndexedSprite.class);
        IndexedSprite indexedSpriteToAdd = Mockito.mock(IndexedSprite.class);
        IndexedSprite[] indexedSprites = {indexedSprite, indexedSprite, indexedSprite};
        given(client.getModIcons()).willReturn(indexedSprites);
        given(client.createIndexedSprite()).willReturn(indexedSpriteToAdd);

        IndexedSprite[] expectedOutput = {indexedSprite, indexedSprite, indexedSprite, indexedSpriteToAdd};

        //when
        hydrateEmojiProvider.loadHydrateEmoji();
        int hydrateEmojiId = hydrateEmojiProvider.getHydrateEmojiId().get();

        //then
        assertEquals(3, hydrateEmojiId);
        verify(client).setModIcons(expectedOutput);
    }

}

