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

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

/**
 * <p>Configuration options interface for the Hydrate Reminder plugin
 * </p>
 * <p>Please see the {@link net.runelite.client.config.Config} class for true identity
 * </p>
 * @author jmakhack
 */
@ConfigGroup("hydratereminder")
public interface HydrateReminderConfig extends Config
{
	/**
	 * Minimum settable number of minutes per interval
	 */
	int INTERVAL_LIMIT_MIN = 1;

	/**
	 * Maximum settable number of minutes per interval
	 */
	int INTERVAL_LIMIT_MAX = 120;

	/**
	 * <p>Allows the player to enable/disable the hydrate login welcome message
	 * </p>
	 * @return true if the welcome message is to be enabled
	 * @since 1.1.0
	 */
	@ConfigItem(
			keyName = "hydrateReminderWelcomeMessageEnabled",
			name = "Welcome message",
			description = "Sets whether or not the welcome message should be displayed",
			position = 1
	)
	default boolean hydrateReminderWelcomeMessageEnabled()
	{
		return false;
	}

	/**
	 * <p>Allows the player to set a hydrate reminder interval in minutes anywhere
	 * between INTERVAL_LIMIT_MIN and INTERVAL_LIMIT_MAX
	 * </p>
	 * @return the number of minutes per hydrate reminder interval
	 * @since 1.0.0
	 */
	@Range(
		min = INTERVAL_LIMIT_MIN,
		max = INTERVAL_LIMIT_MAX
	)
	@ConfigItem(
		keyName = "hydrateReminderInterval",
		name = "Hydrate interval",
		description = "The time interval between each hydrate reminder",
		position = 2
	)
	@Units(Units.MINUTES)
	default int hydrateReminderInterval()
	{
		return 20;
	}

	/**
	 * <p>Allows the player to enable/disable chat message reminders
	 * </p>
	 * @return true if chat message reminders are enabled
	 * @since 1.0.0
	 */
	@ConfigItem(
		keyName = "hydrateReminderChatMessageEnabled",
		name = "Chat notification",
		description = "Sets the hydrate reminder to be sent as a chat message",
		position = 3
	)
	default boolean hydrateReminderChatMessageEnabled()
	{
		return true;
	}

	/**
	 * <p>Allows the player to set the type of chat message reminder
	 * </p>
	 * @return the type of chat message to send reminders with
	 * @since 1.0.0
	 */
	@ConfigItem(
		keyName = "hydrateReminderChatMessageType",
		name = "Chat type",
		description = "Sets the type of chat message sent by hydrate reminder",
		position = 4
	)
	default HydrateReminderChatMessageType hydrateReminderChatMessageType()
	{
		return HydrateReminderChatMessageType.GAMEMESSAGE;
	}

	/**
	 * <p>Allows the player to enable/disable computer tray notification reminders
	 * </p>
	 * @return true if computer tray notification reminders are enabled
	 * @since 1.0.0
	 */
	@ConfigItem(
		keyName = "hydrateReminderComputerNotificationEnabled",
		name = "Computer notification",
		description = "Sets the hydrate reminder to be sent as a computer notification",
		position = 5
	)
	default boolean hydrateReminderComputerNotificationEnabled()
	{
		return false;
	}
}
