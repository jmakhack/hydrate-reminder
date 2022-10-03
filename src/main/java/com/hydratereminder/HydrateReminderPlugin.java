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

import com.google.inject.Provides;
import com.hydratereminder.chat.ChatMessageSender;
import com.hydratereminder.chat.HydrateEmojiProvider;
import com.hydratereminder.command.CommandInvoker;
import com.hydratereminder.command.NotRecognizedCommandException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static com.hydratereminder.Commons.HYDRATE_COMMAND_ALIAS;
import static com.hydratereminder.Commons.HYDRATE_COMMAND_NAME;
import static com.hydratereminder.Commons.RUNELITE_COMMAND_PREFIX;
import static com.hydratereminder.dictionary.HydrateBreakMessageDictionary.getRandomHydrateBreakMessageForPersonality;

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
	description = "Reminds players to stay hydrated during their adventures",
	tags = { "hydrate", "health", "reminder", "hydration", "water", "break", "notification" }
)
public class HydrateReminderPlugin extends Plugin
{


	/**
	 *  Number by which to increment the number of hydration breaks each time a break occurs
	 */
	private static final int HYDRATION_BREAK_INCREMENT = 1;

	/**
	 * RuneLite client object
	 */
	@Inject
	private Client client;

	/** RuneLite client thread */
	@Inject
	private ClientThread clientThread;

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
	 * Manager for Old School Runescape item data
	 */
	@Inject
	private ItemManager itemManager;

	/**
	 * Manager for infoboxes that appear on overlay
	 */
	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ChatMessageSender chatMessageSender;

	@Inject
	private HydrateEmojiProvider hydrateEmojiProvider;

	@Inject
	private CommandInvoker commandDelegate;

	/**
	 * <p>The infobox timer that is rendered onto the overlay
	 * </p>
	 */
	@Getter
	@Setter
	private Optional<HydrateReminderTimer> hydrateReminderTimer = Optional.empty();

	/**
	 * <p>The number of hydration breaks that have occurred
	 * during the current session. It has a default value of zero (0).
	 * </p>
	 */
	@Getter
	@Setter
	private int currentSessionHydrationBreaks = 0;

	/**
	 * <p>The last instant at which a hydrate reminder was dispatched
	 * </p>
	 */
	@Getter
	@Setter
	private Optional<Instant> lastHydrateInstant = Optional.empty();

	/**
	 * <p>The instant at which the player logs in
	 * </p>
	 */
	@Getter
	@Setter
	private Instant loginInstant;

	/**
	 * <p>The current reset state which is true if the hydration interval has been reset
	 * and no breaks have occurred since then
	 * </p>
	 */
	@Getter
	@Setter
	private boolean resetState = false;

	/**
	 * <p>True when game tick is the first one after login which is
	 * used to check if welcome message should be sent
	 * </p>
	 */
	@Getter
	@Setter
	private boolean firstGameTick = true;

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
	 * <p>Detects when a config item has been changed and will show an example Hydrate Reminder notification
	 * based on the config that changed
	 * </p>
	 * @param event the config that has been changed
	 */
	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("hydratereminder"))
		{
			switch (event.getKey())
			{
				case "hydrateReminderChatMessageEnabled":
				case "hydrateReminderChatMessageType":
					// only send example chat notification if chat messages are enabled and player is logged in
					if (config.hydrateReminderChatMessageEnabled() && client.getGameState() == GameState.LOGGED_IN)
					{
						clientThread.invoke(() ->
								chatMessageSender.sendHydrateReminderChatMessage("This is how hydrate reminder chat notifications will appear."));
					}
					break;
				case "hydrateReminderComputerNotificationEnabled":
					if (config.hydrateReminderComputerNotificationEnabled())
					{
						clientThread.invoke(() ->
								sendHydrateReminderNotification("This is how hydrate reminder computer notifications will appear."));
					}
					break;
				default:
					break;
			}
		}
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
			setFirstGameTick(true);
			setResetState(false);
			setLastHydrateInstant(Optional.empty());
			setLoginInstant(Instant.now());
			log.debug("Hydrate Reminder plugin interval timer started");
		}
	}

	/**
	 * <p>Handles any chat commands inputted by the player, executed in the form of ::hr [args]
	 * </p>
	 * @param commandExecuted the chat executed command
	 * @since 1.1.0
	 */
	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted)
    {
    	final String command = commandExecuted.getCommand();
		if (command.equalsIgnoreCase(HYDRATE_COMMAND_NAME) || command.equalsIgnoreCase(HYDRATE_COMMAND_ALIAS))
		{
			final String[] args = commandExecuted.getArguments();
			if (ArrayUtils.isNotEmpty(args))
			{
				try
				{
					final HydrateReminderCommandArgs arg = HydrateReminderCommandArgs.getValue(args[0].toLowerCase());
					switch (arg)
					{
						case NEXT:
						case RESET:
						case HYDRATE:
						case HELP:
						case TOTAL:
						case PREV:
							commandDelegate.invokeCommand(commandExecuted);
							break;
						default:
							throw new IllegalArgumentException();
					}
				}
				catch (IllegalArgumentException | NotRecognizedCommandException e)
				{
					final String invalidArgString = String.format("%s%s %s is not a valid command",
							RUNELITE_COMMAND_PREFIX, HYDRATE_COMMAND_ALIAS, args[0]);
					chatMessageSender.sendHydrateEmojiChatGameMessage(invalidArgString);
					final CommandExecuted helpCommand = new CommandExecuted("hydrate", new String[]{"help"});
					commandDelegate.invokeCommand(helpCommand);
				}
			}
		}
		// TODO: Uncomment when the commands will be refactored to com.hydratereminder.command
		// commandDelegate.invokeCommand(commandExecuted);
	}

	/**
	 * <p>Calculate the duration since the last break
	 * </p>
	 * @param lastHydrateInstant Optional Instant representing the lastHydrateInstant if it exists.
	 * @param currentInstant Instant representing the current instant in time.
	 * @return Optional Duration representing duration from last break till now, if not it is empty.
	 * @since 1.2.0
	 */
	public Optional<Duration> getDurationSinceLastBreak(Optional<Instant> lastHydrateInstant,Instant currentInstant)
	{
		return lastHydrateInstant.map(instant -> Duration.between(instant, currentInstant));
	}

	/**
	 * <p>Handle the String formatting of the specified time.
	 * </p>
	 * @param duration duration to extract time from
	 * @return the time in string format
	 * @since 1.1.1
	 */
	public String getTimeDisplay(Duration duration)
	{
		final int hours = Math.toIntExact(duration.toHours());
		final int minutes = Math.toIntExact(duration.toMinutes() % 60);
		final int seconds = Math.toIntExact((duration.toMillis() / 1000) % 60);
		final StringBuilder timeDisplayBuilder = new StringBuilder();
		if (hours > 0)
		{
			timeDisplayBuilder.append(hours != 1 ? hours + " hours " : hours + " hour ");
		}
		if (minutes > 0 || hours > 0)
		{
			timeDisplayBuilder.append(minutes != 1 ? minutes + " minutes " : minutes + " minute ");
		}
		timeDisplayBuilder.append(seconds != 1 ? seconds + " seconds" : seconds + " second");
		return timeDisplayBuilder.toString();
	}

	/**
	 * <p>Detects if the Hydrate Reminder interval has been reached and runs the appropriate actions
	 * to send out the configured messages to the player and to reset the interval
	 * </p>
	 * @param ignoredEvent game tick event
	 * @since 1.0.0
	 */
	@Subscribe
	public void onGameTick(GameTick ignoredEvent)
	{
		if (isFirstGameTick())
		{
			if (!hydrateEmojiProvider.getHydrateEmojiId().isPresent())
			{
				hydrateEmojiProvider.loadHydrateEmoji();
			}
			if (config.hydrateReminderWelcomeMessageEnabled())
			{
				chatMessageSender.sendHydrateWelcomeChatMessage();
			}
			setFirstGameTick(false);
		}
		// TODO: Improve resource management & performance by not having to remove and create the overlay timer on every game tick to apply updates
		removeHydrateReminderOverlayTimer();
		if (config.hydrateReminderOverlayTimerEnabled())
		{
			createHydrateReminderOverlayTimer();
		}
		final Instant nextHydrateReminderInstant = getNextHydrateReminderInstant();
		if (nextHydrateReminderInstant.compareTo(Instant.now()) < 0)
		{
			setResetState(false);
			handleHydrateReminderDispatch();
			resetHydrateReminderTimeInterval();
			incrementCurrentSessionHydrationBreaks();
		}
	}

	/**
	 * <p>Initializes a new hydrate reminder timer and renders it as an infobox on the
	 * overlay given that one has not been initialized already
	 * </p>
	 * @since 1.2.0
	 */
	private void createHydrateReminderOverlayTimer()
	{
		if (!getHydrateReminderTimer().isPresent())
		{
			final int imageID = config.hydrateReminderOverlayTimerImage().getID();
			final BufferedImage timerImage = itemManager.getImage(imageID);
			final Color timerColor = config.hydrateReminderOverlayTimerTextColor();
			final HydrateReminderTimer newTimer = new HydrateReminderTimer(this, timerImage, timerColor);
			setHydrateReminderTimer(Optional.of(newTimer));
			infoBoxManager.addInfoBox(getHydrateReminderTimer().get());
		}
	}

	/**
	 * <p>Removes the currently initialized hydrate reminder timer if exists
	 * </p>
	 * @since 1.2.0
	 */
	private void removeHydrateReminderOverlayTimer()
	{
		if (getHydrateReminderTimer().isPresent())
		{
			infoBoxManager.removeInfoBox(getHydrateReminderTimer().get());
			hydrateReminderTimer = Optional.empty();
		}
	}

	/**
	 * <p>Calculates the next instant at which the next hydrate reminder should be sent out
	 * </p>
	 * @return the instant to send the next hydrate reminder on
	 * @since 1.1.0
	 */
	public Instant getNextHydrateReminderInstant()
	{
		final Duration hydrateReminderDuration = Duration.ofMinutes(config.hydrateReminderInterval());
		if(getLastHydrateInstant().isPresent())
		{
			return getLastHydrateInstant().get().plus(hydrateReminderDuration);
		}
		return getLoginInstant().plus(hydrateReminderDuration);
	}

	/**
	 * <p>Resets the time interval used for calculating hydrate reminder message dispatching
	 * </p>
	 * @since 1.0.0
	 */
	protected void resetHydrateReminderTimeInterval()
	{
		setLastHydrateInstant(Optional.of(Instant.now()));
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
			chatMessageSender.sendHydrateReminderChatMessage(hydrateReminderMessage);
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
		final String playerName = Objects.requireNonNull(client.getLocalPlayer()).getName();
		final HydrateReminderPersonalityType typeOfPersonality = config.hydrateReminderPersonalityType();
		final String hydrateReminderMessage = getRandomHydrateBreakMessageForPersonality(typeOfPersonality);
		return String.format("%s, %s.", hydrateReminderMessage, playerName);
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
	 * <p>Calculates the number of hydration breaks taken during the current session
	 * </p>
	 * @since 1.2.0
	 */
	public void incrementCurrentSessionHydrationBreaks()
	{
		playAnimationForLocalPlayer();
		setCurrentSessionHydrationBreaks(getCurrentSessionHydrationBreaks() + HYDRATION_BREAK_INCREMENT);
	}

	/**
	 * <p> Takes a hydration break before the interval is finished
	 * </p>
	 * @since 2.0.0
	 */
	public void hydrateBetweenHydrationBreaks()
	{
		incrementCurrentSessionHydrationBreaks();
		resetHydrateReminderTimeInterval();
	}

	/**
	 *
	 * <p> Plays animation for player with given AnimationID
	 * </p>
	 * @since 2.0.0
	 */
	private void playAnimationForLocalPlayer()
	{
		if (config.hydrateAnimationEnabled())
		{
			final Player localPlayer = client.getLocalPlayer();
			localPlayer.setAnimation(AnimationID.CONSUMING);
		}
	}

}
