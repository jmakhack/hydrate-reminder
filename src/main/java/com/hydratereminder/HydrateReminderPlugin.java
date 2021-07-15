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

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import javax.inject.Inject;

import com.google.inject.Provides;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.IndexedSprite;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.ImageUtil;
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
	 * Hydrate Reminder interval break text to display
	 */
	private static final List<String> HYDRATE_BREAK_TEXT_LIST =
			Collections.unmodifiableList(
					new ArrayList<String>() {{
						add("It's time for a quick hydration break");
						add("Dehydration causes fatigue so take a hydration break");
						add("It's time to drink some good ol' water");
						add("Stay healthy by taking a hydration break");
						add("Time to glug, glug, glug some water");
						add("It is now time to take a hydration break");
						add("Time to hydrate");
						add("Power up with a hydration break now");
						add("Got water? It's time to hydrate");
						add("Cheers to this hydration break");
						add("Hydration time is now");
						add("Fuel up with a hydration break");
					}});

	/**
	 * Hydrate Reminder startup welcome text to display
	 */
	private static final List<String> HYDRATE_WELCOME_TEXT_LIST =
			Collections.unmodifiableList(
					new ArrayList<String>() {{
						add("Don't forget to stay hydrated.");
						add("Type \"::hydrate help\" in chat to view available commands.");
						add("Stay cool. Stay awesome. Stay hydrated.");
						add("Keep calm and stay hydrated.");
						add("Cheers to staying hydrated!");
						add("Keep the geyser titans happy by staying hydrated.");
						add("Hydration is love. Hydration is life.");
						add("Out of water? Cast humidify to stay hydrated.");
						add("It costs zero water runes to stay hydrated.");
						add("Check out the hydrate commands by typing \"::hydrate help\" in chat.");
						add("A hydrated adventurer is an unstoppable adventurer.");
						add("It's dangerous to go alone. Stay hydrated!");
					}});

	/**
	 * Prefix for all chat commands in RuneLite
	 */
	private static final String RUNELITE_COMMAND_PREFIX = "::";

	/**
	 * Main command name for the Hydrate Reminder plugin
	 */
	private static final String HYDRATE_COMMAND_NAME = "hydrate";

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
	private Instant lastHydrateInstant = Instant.now();

	/**
	 * The id of the hydrate emoji
	 * Set to -1 if failed to load hydrate emoji sprite
	 */
	private int hydrateEmojiId = -1;

	/**
	 * True when game tick is the first one after login
	 * Used to check if welcome message should be sent
	 */
	private Boolean isFirstGameTick = true;

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
	 * <p>Detects when the player logs in and then starts the Hydrate Reminder interval and
	 * displays the hydrate welcome message
	 * </p>
	 * @param gameStateChanged the change game state event
	 * @since 1.0.0
	 */
	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGING_IN)
		{
			isFirstGameTick = true;
			loadHydrateEmoji();
			resetHydrateReminderTimeInterval();
			log.debug("Hydrate Reminder plugin interval timer started");
		}
	}

	/**
	 * <p>Loads the hydrate emoji image and converts it into a sprite to be used for
	 * chat messages
	 * </p>
	 * @since 1.1.0
	 */
	private void loadHydrateEmoji()
	{
		final IndexedSprite[] modIcons = client.getModIcons();
		if (modIcons != null)
		{
			final IndexedSprite[] newModIcons = Arrays.copyOf(modIcons, modIcons.length + 1);
			try
			{
				final BufferedImage hydrateIcon = ImageUtil.loadImageResource(getClass(), "water_icon.png");
				final IndexedSprite hydrateSprite = ImageUtil.getImageIndexedSprite(hydrateIcon, client);
				newModIcons[modIcons.length] = hydrateSprite;
			}
			catch (Exception e)
			{
				log.warn("Failed to load hydrate emoji sprite", e);
			}
			hydrateEmojiId = modIcons.length;
			client.setModIcons(newModIcons);
			log.debug("Successfully loaded hydrate emoji sprite");
		}
	}

	/**
	 * <p>Sends a random hydrate welcome message in chat
	 * </p>
	 * @since 1.1.0
	 */
	private void sendHydrateWelcomeChatMessage()
	{
		final Random randomGenerator = new Random();
		final String hydrateWelcomeMessage = HYDRATE_WELCOME_TEXT_LIST.get(
				randomGenerator.nextInt(HYDRATE_WELCOME_TEXT_LIST.size()));
		sendHydrateEmojiChatMessage(ChatMessageType.GAMEMESSAGE, hydrateWelcomeMessage);
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
		if (commandExecuted.getCommand().equalsIgnoreCase(HYDRATE_COMMAND_NAME))
		{
			final String[] args = commandExecuted.getArguments();
			if (ArrayUtils.isNotEmpty(args))
			{
				try
				{
					final HydrateReminderCommandArgs arg = HydrateReminderCommandArgs.valueOf(args[0].toUpperCase());
					switch (arg)
					{
						case NEXT:
							handleHydrateNextCommand();
							break;
						case PREV:
							handleHydratePrevCommand();
							break;
						case RESET:
							handleHydrateResetCommand();
							break;
						case HELP:
							handleHydrateHelpCommand();
							break;
						default:
							throw new IllegalArgumentException();
					}
				}
				catch (IllegalArgumentException e)
				{
					final String invalidArgString = String.format("%s%s %s is not a valid command",
							RUNELITE_COMMAND_PREFIX, HYDRATE_COMMAND_NAME, args[0]);
					sendHydrateEmojiChatMessage(ChatMessageType.GAMEMESSAGE, invalidArgString);
					handleHydrateHelpCommand();
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
		final int hours = Math.toIntExact(timeUntilNextBreak.toHours());
		final int minutes = Math.toIntExact(timeUntilNextBreak.toMinutes() % 60);
		final int seconds = Math.toIntExact((timeUntilNextBreak.toMillis() / 1000) % 60);
		final String timeString = timeDisplay(hours, minutes, seconds);
		sendHydrateEmojiChatMessage(ChatMessageType.GAMEMESSAGE, timeString);
	}

	/**
	 * <p>Handle the hydrate prev command by generating a chat message displaying the amount of time
	 * since the last hydrate break
	 * </p>
	 * @since 1.1.0
	 */
	private void handleHydratePrevCommand()
	{
		final Duration timeSinceLastBreak = Duration.between(lastHydrateInstant, Instant.now());
		final int hours = Math.toIntExact(timeSinceLastBreak.toHours());
		final int minutes = Math.toIntExact(timeSinceLastBreak.toMinutes() % 60);
		final int seconds = Math.toIntExact((timeSinceLastBreak.toMillis() / 1000) % 60);
		final String timeString = timeDisplay(hours, minutes, seconds);
		sendHydrateEmojiChatMessage(ChatMessageType.GAMEMESSAGE, timeString);
	}

	/**
	 * <p>Handle the String formatting of the specified time.</p>
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return Time in a string format
	 * @since 1.1.1
	 */
	String timeDisplay(int hours, int minutes, int seconds) {
		String hoursRepresentation = hours != 1 ? hours + " hours" : hours + " hour";
		String minutesRepresentation = minutes != 1 ? minutes + " minutes" : minutes + " minute";
		String secondesRepresentation = seconds != 1 ? seconds + " secondes" : seconds + " second";
		String timeDisplayString = String.format("%s %s %s until the next hydrate break",
				hoursRepresentation, minutesRepresentation, secondesRepresentation);
		return timeDisplayString;
	}

	/**
	 * <p>Handle the hydrate reset command by resetting the current hydrate interval and displaying
	 * a reset success message in chat
	 * </p>
	 * @since 1.1.0
	 */
	private void handleHydrateResetCommand()
	{
		resetHydrateReminderTimeInterval();
		final String resetString = "Hydrate reminder interval has been successfully reset";
		sendHydrateEmojiChatMessage(ChatMessageType.GAMEMESSAGE, resetString);
	}

	/**
	 * <p>Handle the hydrate help command by displaying all available command arguments
	 * </p>
	 * @since 1.1.0
	 */
	private void handleHydrateHelpCommand()
	{
		final StringBuilder commandList = new StringBuilder();
		final String listSeparator = ", ";
		for (HydrateReminderCommandArgs arg : HydrateReminderCommandArgs.values())
		{
			if (commandList.length() > 0)
			{
				commandList.append(listSeparator);
			}
			commandList.append(arg.toString());
		}
		final String helpString = String.format("Available commands: %s%s %s",
				RUNELITE_COMMAND_PREFIX, HYDRATE_COMMAND_NAME, commandList);
		sendHydrateEmojiChatMessage(ChatMessageType.GAMEMESSAGE, helpString);
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
		if (isFirstGameTick && config.hydrateReminderWelcomeMessageEnabled())
		{
			sendHydrateWelcomeChatMessage();
			isFirstGameTick = false;
		}
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
		final Random randomGenerator = new Random();
		final String playerName = Objects.requireNonNull(client.getLocalPlayer()).getName();
		final String hydrateReminderMessage = HYDRATE_BREAK_TEXT_LIST.get(
				randomGenerator.nextInt(HYDRATE_BREAK_TEXT_LIST.size()));
		return String.format("%s, %s.", hydrateReminderMessage, playerName);
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
		sendHydrateEmojiChatMessage(chatMessageType, message);
		log.debug(String.format("Successfully sent chat notification of type: %s", chatMessageType.toString()));
	}

	/**
	 * <p>Generates and sends a neatly formatted chat message prefixed by the
	 * hydrate emoji to the player
	 * </p>
	 * @param type the type of chat message to send
	 * @param message the hydrate reminder message to display to the player
	 * @since 1.1.0
	 */
	private void sendHydrateEmojiChatMessage(ChatMessageType type, String message)
	{
		if (hydrateEmojiId == -1)
		{
			client.addChatMessage(type, "", message, null);
			return;
		}
		final String hydrateEmoji = String.format("<img=%d>", hydrateEmojiId);
		final StringBuilder hydrateMessage = new StringBuilder();
		String sender = hydrateEmoji;
		if (type == ChatMessageType.BROADCAST)
		{
			hydrateMessage.append(" ");
		}
		if (type != ChatMessageType.FRIENDSCHAT)
		{
			hydrateMessage.append(hydrateEmoji);
			hydrateMessage.append(" ");
			sender = null;
		}
		hydrateMessage.append(message);
		client.addChatMessage(type, "", hydrateMessage.toString(), sender);
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
