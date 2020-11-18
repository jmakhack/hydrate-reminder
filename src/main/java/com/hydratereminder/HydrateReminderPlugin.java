package com.hydratereminder;

import com.google.inject.Provides;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.GameState;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.Notifier;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Hydrate Reminder",
	description = "Reminds players to take a hydration break on a set interval",
	tags = { "hydrate", "health", "reminder", "hydration", "water", "break", "notification" }
)

public class HydrateReminderPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HydrateReminderConfig config;

	@Inject
	private Notifier notifier;

	private Instant lastHydrateInstant;

	private void resetHydrateReminderTimeInterval()
	{
		lastHydrateInstant = Instant.now();
	}

	private ChatMessageType getChatNotificationMessageType()
	{
		ChatMessageType chatMessageType;
		switch (config.hydrateReminderChatMessageType())
		{
			case BROADCASTMESSAGE:
				chatMessageType = ChatMessageType.BROADCAST;
				break;
			case PUBLICCHAT:
				chatMessageType = ChatMessageType.PUBLICCHAT;
				break;
			case CLANCHAT:
				chatMessageType = ChatMessageType.FRIENDSCHAT;
				break;
			default:
				chatMessageType = ChatMessageType.GAMEMESSAGE;
				break;
		}
		return chatMessageType;
	}

	@Override
	protected void startUp() throws Exception
	{
		log.info("Hydrate Reminder plugin started");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Hydrate Reminder plugin stopped");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			resetHydrateReminderTimeInterval();
			log.info("Hydrate Reminder plugin interval timer started");
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		final Duration hydrateReminderDuration = Duration.ofMinutes(config.hydrateReminderInterval());
		if (lastHydrateInstant.plus(hydrateReminderDuration).compareTo(Instant.now()) < 0)
		{
			final String hydrateReminderMessage = String.format("It's time for a quick hydration break, %s.", client.getLocalPlayer().getName());
			if (config.hydrateReminderChatMessageEnabled())
			{
				final ChatMessageType chatMessageType = getChatNotificationMessageType();
				final String chatMessageSender = chatMessageType == ChatMessageType.FRIENDSCHAT ? "HydrateReminder" : "";
				client.addChatMessage(chatMessageType, "", hydrateReminderMessage, chatMessageSender);
				log.info(String.format("Successfully sent chat notification of type: %s", chatMessageType.toString()));
			}
			if (config.hydrateReminderComputerNotificationEnabled())
			{
				notifier.notify(hydrateReminderMessage);
				log.info("Successfully sent computer notification");
			}
			resetHydrateReminderTimeInterval();
		}
	}

	@Provides
	HydrateReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HydrateReminderConfig.class);
	}
}
