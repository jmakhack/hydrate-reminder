package com.hydratereminder;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import javax.inject.Inject;

import com.google.inject.Provides;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.ArrayUtils;

/**
 * <p>The main plugin logic for the Hydrate Reminder plugin
 * </p>
 * <p>Please see the {@link net.runelite.client.plugins.Plugin} class for true identity
 * </p>
 * @author jmakhack
 */
@Slf4j
@PluginDescriptor(
	name = "Hydrate Reminder",
	description = "Reminds players to take a hydration break on a set interval",
	tags = { "hydrate", "health", "reminder", "hydration", "water", "break", "notification" }
)
public class HydrateReminderPlugin extends Plugin
{
	/**
	 * Hydrate Reminder text to display
	 */
	private static final List<String> HYDRATE_BREAK_TEXT_LIST =
			Collections.unmodifiableList(
					new ArrayList<>() {{
						add("It's time for a quick hydration break");
						add("70% of the human brain is water so take a hydration break");
						add("Dehydration causes fatigue, take a quick hydration break");
						add("Drink water!");
						add("Drink water to stay healthy");
						add("Hey you, drink some water");
					}});

	/**
	 * Username of Hydrate Reminder plugin to display in chatbox
	 */
	private static final String HYDRATE_REMINDER_USERNAME = "HydrateReminder";

	/**
	 * RuneLite client object
	 */
	@Inject
	private Client client;

	/**
	 * Configuration settings for Hydrate Reminder plugin
	 */
	@Inject
	private HydrateReminderConfig config;

	/**
	 * Notifier object for managing computer tray notifications
	 */
	@Inject
	private Notifier notifier;

	/**
	 * The last instant at which a hydrate reminder was dispatched
	 */
	private Instant lastHydrateInstant;

	/**
	 * <p>Provides the configuration for the Hydrate Reminder plugin
	 * </p>
	 * @param configManager the plugin configuration manager
	 * @return Hydrate Reminder configuration
	 * @since 1.0.0
	 */
	@Provides
	protected HydrateReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HydrateReminderConfig.class);
	}

	/**
	 * <p>Detects when the player logs in and then starts the Hydrate Reminder interval
	 * </p>
	 * @param gameStateChanged the change game state event
	 * @since 1.0.0
	 */
	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			resetHydrateReminderTimeInterval();
			log.debug("Hydrate Reminder plugin interval timer started");
		}
	}

	/**
	 * <p>Handles any chat commands inputted by the player, executed in the form of ::hydrate [args]
	 * </p>
	 * @param commandExecuted the chat executed command
	 * @since 1.1.0
	 */
	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted)
    {
		if (commandExecuted.getCommand().equalsIgnoreCase("hydrate"))
		{
			final String[] args = commandExecuted.getArguments();
			if (ArrayUtils.isNotEmpty(args))
			{
				switch (args[0].toLowerCase())
				{
					case "next":
						handleHydrateNextCommand();
						break;
					default:
						log.warn(String.format("%s is not a supported argument for the hydrate command", args[0]));
						break;
				}
			}
		}
	}

	/**
	 * <p>Handle the hydrate next command by generating a chat message displaying the amount of time
	 * until the next hydrate break
	 * </p>
	 * @since 1.1.0
	 */
	private void handleHydrateNextCommand()
	{
		final Instant nextHydrateReminderInstant = getNextHydrateReminderInstant();
		final Duration timeUntilNextBreak = Duration.between(Instant.now(), nextHydrateReminderInstant);
		final String timeString = String.format("%s hours %s minutes %s seconds until next hydrate break",
				timeUntilNextBreak.toHoursPart(), timeUntilNextBreak.toMinutesPart(),
				timeUntilNextBreak.toSecondsPart());
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", timeString, null);
	}

	/**
	 * <p>Detects if the Hydrate Reminder interval has been reached and runs the appropriate actions
	 * to send out the configured messages to the player and to reset the interval
	 * </p>
	 * @param event game tick event
	 * @since 1.0.0
	 */
	@Subscribe
	public void onGameTick(GameTick event)
	{
		final Instant nextHydrateReminderInstant = getNextHydrateReminderInstant();
		if (nextHydrateReminderInstant.compareTo(Instant.now()) < 0)
		{
			handleHydrateReminderDispatch();
			resetHydrateReminderTimeInterval();
		}
	}

	/**
	 * <p>Calculates the next instant at which the next hydrate reminder should be sent out
	 * </p>
	 * @return the instant to send the next hydrate reminder on
	 * @since 1.1.0
	 */
	private Instant getNextHydrateReminderInstant()
	{
		final Duration hydrateReminderDuration = Duration.ofMinutes(config.hydrateReminderInterval());
		return lastHydrateInstant.plus(hydrateReminderDuration);
	}

	/**
	 * <p>Resets the time interval used for calculating hydrate reminder message dispatching
	 * </p>
	 * @since 1.0.0
	 */
	private void resetHydrateReminderTimeInterval()
	{
		lastHydrateInstant = Instant.now();
	}

	/**
	 * <p>Handles the dispatching of hydrate reminders to the appropriate methods that send
	 * the different hydrate reminder message types to the player
	 * </p>
	 * @since 1.1.0
	 */
	private void handleHydrateReminderDispatch()
	{
		final String hydrateReminderMessage = getHydrateReminderMessage();
		if (config.hydrateReminderChatMessageEnabled())
		{
			sendHydrateReminderChatMessage(hydrateReminderMessage);
		}
		if (config.hydrateReminderComputerNotificationEnabled())
		{
			sendHydrateReminderNotification(hydrateReminderMessage);
		}
	}

	/**
	 * <p>Generates the hydrate reminder message to display to the player by choosing random
	 * element from the list of available messages.
	 * </p>
	 * @return the hydrate reminder message to display to the player
	 * @since 1.1.0
	 */
	private String getHydrateReminderMessage()
	{

		Random randomGenerator = new Random();
		final String playerName = Objects.requireNonNull(client.getLocalPlayer()).getName();
		String hydrateReminderMessage = HYDRATE_BREAK_TEXT_LIST.get(
				randomGenerator.nextInt(HYDRATE_BREAK_TEXT_LIST.size()));
		return String.format("%s, %s", hydrateReminderMessage, playerName);
	}

	/**
	 * <p>Sends a hydrate reminder message to the player via the ingame chat box
	 * </p>
	 * @param message the hydrate reminder message to display to the player
	 * @since 1.1.0
	 */
	private void sendHydrateReminderChatMessage(String message)
	{
		final ChatMessageType chatMessageType = getChatNotificationMessageType();
		final String chatMessageSender = chatMessageType == ChatMessageType.FRIENDSCHAT ?
				HYDRATE_REMINDER_USERNAME : "";
		client.addChatMessage(chatMessageType, "", message, chatMessageSender);
		log.debug(String.format("Successfully sent chat notification of type: %s", chatMessageType.toString()));
	}

	/**
	 * <p>Sends a hydrate reminder message to the player via computer tray notification
	 * </p>
	 * @param message the hydrate reminder message to display to the player
	 * @since 1.1.0
	 */
	private void sendHydrateReminderNotification(String message)
	{
		notifier.notify(message);
		log.debug("Successfully sent computer notification");
	}

	/**
	 * <p>Generates the type of chat message to send to the player
	 * </p>
	 * @return the type of chat message to send to the player
	 * @since 1.0.0
	 */
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
}
