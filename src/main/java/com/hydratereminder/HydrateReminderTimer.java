/*
 * Copyright (c) 2021, jmakhack <https://github.com/jmakhack>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.hydratereminder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

/**
 * <p>The infobox that displays the time remaining until the next hydration break
 * </p>
 * <p>Please see the {@link net.runelite.client.ui.overlay.infobox.InfoBox} class for true identity
 * </p>
 * @author jmakhack
 */
@Getter
@ToString
public class HydrateReminderTimer extends InfoBox
{
    private static final String TOOLTIP_INFORMATION = "Time until next hydration break";

    /**
     * <p>The text color to display in the hydrate reminder infobox
     * </p>
     */
    @Getter
    @Setter
    private Color textColor;

    /**
     * Hydrate reminder plugin that created the timer
     */
    private final HydrateReminderPlugin hydrateReminderPlugin;

    /**
     * <p>Main constructor to initialize a new hydrate reminder timer
     * </p>
     * @param plugin the plugin creating the timer
     * @param image the background image for the infobox
     * @param timerColor the color to use for the timer text
     * @since 1.2.0
     */
    HydrateReminderTimer(HydrateReminderPlugin plugin, BufferedImage image, Color timerColor)
    {
        super(image, plugin);
        setPriority(InfoBoxPriority.MED);
        hydrateReminderPlugin = plugin;
        textColor = timerColor;
    }

    /**
     * <p>Gets the current text that is displayed in the timer infobox
     * </p>
     * @return text displayed in timer infobox
     * @since 1.2.0
     */
    @Override
    public String getText()
    {
        final Instant endTime = hydrateReminderPlugin.getNextHydrateReminderInstant();
        final Duration timeRemaining = Duration.between(Instant.now(), endTime);
        final int seconds = (int) ((timeRemaining.toMillis() / 1000L) % 60);
        final int minutes = (int) (timeRemaining.toMillis() / 60_000L);
        return String.format("%d:%02d", Math.max(minutes, 0), Math.max(seconds, 0));
    }

    /**
     * <p>Gets the text that is displayed in the tooltip
     * </p>
     * @return text displayed when mouse cursor is hovered over infobox
     */
    @Override
    public String getTooltip() {
        return TOOLTIP_INFORMATION;
    }
}
