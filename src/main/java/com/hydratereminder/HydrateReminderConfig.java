package com.hydratereminder;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

@ConfigGroup("hydratereminder")
public interface HydrateReminderConfig extends Config
{
	int INTERVAL_LIMIT_MIN = 1;
	int INTERVAL_LIMIT_MAX = 120;

	@Range(
		min = INTERVAL_LIMIT_MIN,
		max = INTERVAL_LIMIT_MAX
	)

	@ConfigItem(
		keyName = "hydrateReminderInterval",
		name = "Hydrate Interval",
		description = "The time interval between each hydrate reminder",
		position = 1
	)

	@Units(Units.MINUTES)
	default int hydrateReminderInterval()
	{
		return 30;
	}

	@ConfigItem(
		keyName = "hydrateReminderChatMessageEnabled",
		name = "Chat notification",
		description = "Sets the hydrate reminder to be sent as a game chat message",
		position = 3
	)

	default boolean hydrateReminderGameChatMessageEnabled()
	{
		return true;
	}

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

	@ConfigItem(
		keyName = "hydrateReminderComputerNotificationEnabled",
		name = "Computer notification",
		description = "Sets the hydrate reminder to be sent as a computer notification",
		position = 5
	)

	default boolean hydrateReminderComputerNotificationEnabled()
	{
		return true;
	}
}
