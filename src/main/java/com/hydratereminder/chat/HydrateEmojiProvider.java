package com.hydratereminder.chat;

import com.hydratereminder.HydrateReminderPlugin;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.IndexedSprite;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Singleton
public class HydrateEmojiProvider {

    /**
     * RuneLite client object
     */
    @Inject
    private transient Client client;

    /**
     * <p>The id of the hydrate emoji
     * </p>
     */
    @Getter
    @Setter
    private Optional<Integer> hydrateEmojiId = Optional.empty();

    /**
     * <p>Loads the hydrate emoji image and converts it into a sprite to be used for
     * chat messages
     * </p>
     */
    public void loadHydrateEmoji() {
        final IndexedSprite[] modIcons = client.getModIcons();
        if (modIcons != null) {
            final IndexedSprite[] newModIcons = Arrays.copyOf(modIcons, modIcons.length + 1);
            try {
                final BufferedImage hydrateIcon = ImageUtil.loadImageResource(HydrateReminderPlugin.class, "water_icon.png");
                final IndexedSprite hydrateSprite = ImageUtil.getImageIndexedSprite(hydrateIcon, client);
                newModIcons[modIcons.length] = hydrateSprite;
            } catch (RuntimeException e) {
                log.warn("Failed to load hydrate emoji sprite", e);
            }
            setHydrateEmojiId(Optional.of(modIcons.length));
            client.setModIcons(newModIcons);
            log.debug("Successfully loaded hydrate emoji sprite");
        }
    }
}

