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

@ExtendWith(MockitoExtension.class)
public class HydrateEmojiProviderTest {

    @Mock
    private Client client;

    @InjectMocks
    private HydrateEmojiProvider hydrateEmojiProvider;


    @Test
    public void shouldSetHydrateEmojiIdTo2WhenHydrateEmojiIsLoadedAndHave2Elements() {
        //given
        IndexedSprite indexedSprite = Mockito.mock(IndexedSprite.class);
        IndexedSprite[] indexedSprites = {indexedSprite, indexedSprite, indexedSprite};
        given(client.getModIcons()).willReturn(indexedSprites);

        //when
        hydrateEmojiProvider.loadHydrateEmoji();
        int hydrateEmojiId = hydrateEmojiProvider.getHydrateEmojiId().get();

        //then
        assertEquals(3, hydrateEmojiId);
    }

}

