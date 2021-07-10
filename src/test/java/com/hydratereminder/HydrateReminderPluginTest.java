package com.hydratereminder;

import mockit.*;
import mockit.integration.junit4.JMockit;
import net.runelite.api.*;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

import static mockit.Deencapsulation.*;
import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class HydrateReminderPluginTest
{
    @Injectable
    private Client client;

    @Injectable
    private HydrateReminderConfig config;

    @Injectable
    private Notifier notifier;

    @Tested
    private HydrateReminderPlugin plugin;

    @Test
    public void testProvideConfig(@Mocked ConfigManager configManager)
    {
        new Expectations()
        {{
            configManager.getConfig(HydrateReminderConfig.class);
            result = config;
            times = 1;
        }};
        HydrateReminderConfig hydrateReminderConfig = plugin.provideConfig(configManager);
        assertEquals(config, hydrateReminderConfig);
    }

    @Test
    public void testOnGameStateChangedLogIn(@Mocked GameStateChanged gameStateChanged)
    {
        new Expectations()
        {{
            gameStateChanged.getGameState();
            result = GameState.LOGGED_IN;
            times = 1;
        }};
        Instant now = getField(plugin, "lastHydrateInstant");
        plugin.onGameStateChanged(gameStateChanged);
        Instant resetInstant = getField(plugin, "lastHydrateInstant");
        assertNotEquals(null, resetInstant);
        assertNotEquals(now, resetInstant);
    }

    @Test
    public void testOnGameStateChangedNotLogIn(@Mocked GameStateChanged gameStateChanged)
    {
        new Expectations()
        {{
            gameStateChanged.getGameState();
            result = GameState.UNKNOWN;
            times = 1;
        }};
        Instant now = getField(plugin, "lastHydrateInstant");
        plugin.onGameStateChanged(gameStateChanged);
        Instant resetInstant = getField(plugin, "lastHydrateInstant");
        assertNotEquals(null, resetInstant);
        assertEquals(now, resetInstant);
    }

    @Test
    public void testOnCommandExecutedInvalidCommand(@Mocked CommandExecuted commandExecuted)
    {
        new Expectations(plugin)
        {{
            commandExecuted.getCommand();
            result = "hydration";
            times = 1;
        }};
        plugin.onCommandExecuted(commandExecuted);
        new Verifications()
        {{
            invoke(plugin, "handleHydrateNextCommand");
            times = 0;
        }};
    }

    @Test
    public void testOnCommandExecutedEmptyArguments(@Mocked CommandExecuted commandExecuted)
    {
        new Expectations(plugin)
        {{
            commandExecuted.getCommand();
            result = "hydrate";
            times = 1;
            commandExecuted.getArguments();
            result = new String[] {};
            times = 1;
        }};
        plugin.onCommandExecuted(commandExecuted);
        new Verifications()
        {{
            invoke(plugin, "handleHydrateNextCommand");
            times = 0;
        }};
    }

    @Test
    public void testOnCommandExecutedInvalidArguments(@Mocked CommandExecuted commandExecuted)
    {
        new Expectations(plugin)
        {{
            commandExecuted.getCommand();
            result = "hydrate";
            times = 1;
            commandExecuted.getArguments();
            result = new String[] { "start" };
            times = 1;
        }};
        plugin.onCommandExecuted(commandExecuted);
        new Verifications()
        {{
            invoke(plugin, "handleHydrateNextCommand");
            times = 0;
        }};
    }

    @Test
    public void testOnCommandExecutedUppercase(@Mocked CommandExecuted commandExecuted)
    {
        new Expectations(plugin)
        {{
            commandExecuted.getCommand();
            result = "HYDRATE";
            times = 1;
            commandExecuted.getArguments();
            result = new String[] { "NEXT" };
            times = 1;
        }};
        setField(plugin, "lastHydrateInstant", Instant.now());
        plugin.onCommandExecuted(commandExecuted);
        new Verifications()
        {{
            invoke(plugin, "handleHydrateNextCommand");
            times = 1;
        }};
    }

    @Test
    public void testOnCommandExecutedNext(@Mocked CommandExecuted commandExecuted)
    {
        new Expectations(plugin)
        {{
            commandExecuted.getCommand();
            result = "hydrate";
            times = 1;
            commandExecuted.getArguments();
            result = new String[] { "next" };
            times = 1;
        }};
        setField(plugin, "lastHydrateInstant", Instant.now());
        plugin.onCommandExecuted(commandExecuted);
        new Verifications()
        {{
            invoke(plugin, "handleHydrateNextCommand");
            times = 1;
        }};
    }

    @Test
    public void testHandleHydrateNextCommand()
    {
        new Expectations(plugin)
        {{
            invoke(plugin, "getNextHydrateReminderInstant");
            result = Instant.now().plusSeconds(4000);
            times = 1;
        }};
        invoke(plugin, "handleHydrateNextCommand");
        new Verifications()
        {{
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
                    withSubstring("1 hours 6 minutes"), null);
            times = 1;
        }};
    }

    @Test
    public void testHandleHydratePrevCommand()
    {
        setField(plugin, "lastHydrateInstant", Instant.now().minusSeconds(5000));
        invoke(plugin, "handleHydratePrevCommand");
        new Verifications()
        {{
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
                    withSubstring("1 hours 23 minutes"), null);
            times = 1;
        }};
    }

    @Test
    public void testOnGameTickIntervalReached()
    {
        final Instant now = Instant.now();
        new Expectations(plugin)
        {{
            invoke(plugin, "getNextHydrateReminderInstant");
            result = now.minusSeconds(10000);
            times = 1;
            invoke(plugin, "handleHydrateReminderDispatch");
            result = null;
            times = 1;
        }};
        setField(plugin, "lastHydrateInstant", now);
        plugin.onGameTick(null);
        final Instant resetInstant = getField(plugin, "lastHydrateInstant");
        assertNotEquals(now, resetInstant);
    }

    @Test
    public void testOnGameTickIntervalNotReached()
    {
        new Expectations(plugin)
        {{
            config.hydrateReminderInterval();
            result = 99999999;
            times = 1;
            invoke(plugin, "handleHydrateReminderDispatch");
            times = 0;
        }};
        final Instant now = Instant.now();
        setField(plugin, "isFirstGameTick", false);
        setField(plugin, "lastHydrateInstant", now);
        plugin.onGameTick(null);
        final Instant resetInstant = getField(plugin, "lastHydrateInstant");
        assertEquals(now, resetInstant);
    }

    @Test
    public void testGetNextHydrateReminderInstant()
    {
        new Expectations(plugin)
        {{
            config.hydrateReminderInterval();
            result = 5;
            times = 1;
        }};
        final Instant now = Instant.now();
        setField(plugin, "lastHydrateInstant", now);
        final Instant nextInstant = invoke(plugin, "getNextHydrateReminderInstant");
        assertTrue(nextInstant.compareTo(now) > 0);
        assertTrue(nextInstant.compareTo(now.plusSeconds(360)) < 0);
    }

    @Test
    public void testResetHydrateReminderTimeInterval() throws InterruptedException {
        final Instant now = getField(plugin, "lastHydrateInstant");
        Thread.sleep(100);
        invoke(plugin, "resetHydrateReminderTimeInterval");
        final Instant resetInstant = getField(plugin, "lastHydrateInstant");
        assertNotEquals(null, resetInstant);
        assertNotEquals(now, resetInstant);
    }

    @Test
    public void testHandleHydrateReminderDispatchAll()
    {
        final String message = "Ready to hydrate";
        new Expectations(plugin)
        {{
            invoke(plugin, "getHydrateReminderMessage");
            result = message;
            times = 1;
            config.hydrateReminderChatMessageEnabled();
            result = true;
            times = 1;
            invoke(plugin, "sendHydrateReminderChatMessage", message);
            times = 1;
            config.hydrateReminderComputerNotificationEnabled();
            result = true;
            times = 1;
            invoke(plugin, "sendHydrateReminderNotification", message);
            times = 1;
        }};
        invoke(plugin, "handleHydrateReminderDispatch");
    }

    @Test
    public void testHandleHydrateReminderDispatchChatMessage()
    {
        final String message = "Take a hydration break";
        new Expectations(plugin)
        {{
            invoke(plugin, "getHydrateReminderMessage");
            result = message;
            times = 1;
            config.hydrateReminderChatMessageEnabled();
            result = true;
            times = 1;
            invoke(plugin, "sendHydrateReminderChatMessage", message);
            times = 1;
            config.hydrateReminderComputerNotificationEnabled();
            result = false;
            times = 1;
            invoke(plugin, "sendHydrateReminderNotification", message);
            times = 0;
        }};
        invoke(plugin, "handleHydrateReminderDispatch");
    }

    @Test
    public void testHandleHydrateReminderDispatchTrayNotification()
    {
        final String message = "Hydrate break time";
        new Expectations(plugin)
        {{
            invoke(plugin, "getHydrateReminderMessage");
            result = message;
            times = 1;
            config.hydrateReminderChatMessageEnabled();
            result = false;
            times = 1;
            invoke(plugin, "sendHydrateReminderChatMessage", message);
            times = 0;
            config.hydrateReminderComputerNotificationEnabled();
            result = true;
            times = 1;
            invoke(plugin, "sendHydrateReminderNotification", message);
            times = 1;
        }};
        invoke(plugin, "handleHydrateReminderDispatch");

    }

    @Test
    public void testHandleHydrateReminderDispatchNone()
    {
        final String message = "Hydrate Test";
        new Expectations(plugin)
        {{
            invoke(plugin, "getHydrateReminderMessage");
            result = message;
            times = 1;
            config.hydrateReminderChatMessageEnabled();
            result = false;
            times = 1;
            invoke(plugin, "sendHydrateReminderChatMessage", message);
            times = 0;
            config.hydrateReminderComputerNotificationEnabled();
            result = false;
            times = 1;
            invoke(plugin, "sendHydrateReminderNotification", message);
            times = 0;
        }};
        invoke(plugin, "handleHydrateReminderDispatch");
    }

    @Test
    public void testGetHydrateReminderMessage(@Mocked Player playerMock)
    {
        final String playerName = "OSRSplayer42";
        new Expectations()
        {{
            client.getLocalPlayer();
            result = playerMock;
            times = 1;
            playerMock.getName();
            result = playerName;
            times = 1;
        }};
        final List<String> hydrateMessageList = getField(plugin, "HYDRATE_BREAK_TEXT_LIST");
        final String message = invoke(plugin, "getHydrateReminderMessage");
        assertTrue(hydrateMessageList.contains(message.replace(", OSRSplayer42.", "")));
    }

    @Test
    public void testSendHydrateReminderChatMessage()
    {
        final ChatMessageType messageType = ChatMessageType.GAMEMESSAGE;
        final String message = "Time to Hydrate";
        new Expectations(plugin) {{
            invoke(plugin, "getChatNotificationMessageType");
            result = messageType;
            times = 1;
            invoke(plugin, "sendHydrateEmojiChatMessage", messageType, message);
            times = 1;
        }};
        invoke(plugin, "sendHydrateReminderChatMessage", message);
    }

    @Test
    public void testSendHydrateReminderClanChatMessage()
    {
        final ChatMessageType messageType = ChatMessageType.FRIENDSCHAT;
        final String message = "Hydrate now";
        new Expectations(plugin) {{
            invoke(plugin, "getChatNotificationMessageType");
            result = messageType;
            times = 1;
            invoke(plugin, "sendHydrateEmojiChatMessage", messageType, message);
            times = 1;
        }};
        invoke(plugin, "sendHydrateReminderChatMessage", message);
    }

    @Test
    public void testSendHydrateReminderNotification()
    {
        final String message = "Break to Hydrate";
        invoke(plugin, "sendHydrateReminderNotification", message);
        new Verifications()
        {{
            notifier.notify(message);
            times = 1;
        }};
    }

    @Test
    public void testGetChatNotificationMessageType()
    {
        new Expectations()
        {{
            config.hydrateReminderChatMessageType();
            result = HydrateReminderChatMessageType.BROADCASTMESSAGE;
            times = 1;
        }};
        final ChatMessageType messageType = invoke(plugin, "getChatNotificationMessageType");
        assertEquals(ChatMessageType.BROADCAST, messageType);
    }
}
