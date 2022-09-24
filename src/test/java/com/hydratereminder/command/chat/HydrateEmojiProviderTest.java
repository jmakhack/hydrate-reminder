package com.hydratereminder.command.chat;

import junit.framework.TestCase;
import net.runelite.api.Client;
import net.runelite.api.IndexedSprite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class HydrateEmojiProviderTest extends TestCase {

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

