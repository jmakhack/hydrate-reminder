package com.hydratereminder;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
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

    static final class PlayerImpl implements Player {
        public int getCombatLevel() { return 0; }
        public String getName() { return null; }
        public Actor getInteracting() { return null; }
        public int getHealthRatio() { return 0; }
        public int getHealthScale() { return 0; }
        public WorldPoint getWorldLocation() { return null; }
        public LocalPoint getLocalLocation() { return null; }
        public void setIdlePoseAnimation(int animation) {}
        public void setPoseAnimation(int animation) {}
        public int getOrientation() { return 0; }
        public int getAnimation() { return 0; }
        public int getPoseAnimation() { return 0; }
        public int getIdlePoseAnimation() { return 0; }
        public void setAnimation(int animation) {}
        public void setActionFrame(int actionFrame) {}
        public int getGraphic() { return 0; }
        public void setGraphic(int graphic) {}
        public void setSpotAnimFrame(int spotAnimFrame) {}
        public Polygon getCanvasTilePoly() { return null; }
        public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset) { return null; }
        public Point getCanvasImageLocation(BufferedImage image, int zOffset) { return null; }
        public Point getCanvasSpriteLocation(SpritePixels sprite, int zOffset) { return null; }
        public Point getMinimapLocation() { return null; }
        public int getLogicalHeight() { return 0; }
        public Shape getConvexHull() { return null; }
        public WorldArea getWorldArea() { return null; }
        public String getOverheadText() { return null; }
        public void setOverheadText(String overheadText) {}
        public boolean isDead() { return false; }
        public PlayerComposition getPlayerComposition() { return null; }
        public Polygon[] getPolygons() { return new Polygon[0]; }
        public int getTeam() { return 0; }
        public boolean isFriendsChatMember() { return false; }
        public boolean isFriend() { return false; }
        public HeadIcon getOverheadIcon() { return null; }
        public SkullIcon getSkullIcon() { return null; }
        public Model getModel() { return null; }
        public int getModelHeight() { return 0; }
        public void setModelHeight(int modelHeight) {}
        public void draw(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z, long hash) {}
        public Node getNext() { return null; }
        public Node getPrevious() { return null; }
        public long getHash() { return 0; }
    };

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
        assertEquals(null, resetInstant);
        assertEquals(now, resetInstant);
    }

    @Test
    public void testOnGameTickIntervalReached()
    {
        new Expectations(plugin)
        {{
            config.hydrateReminderInterval();
            result = 0;
            times = 1;
            invoke(plugin, "handleHydrateReminderDispatch");
            result = null;
            times = 1;
        }};
        final Instant now = Instant.now();
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
        setField(plugin, "lastHydrateInstant", now);
        plugin.onGameTick(null);
        final Instant resetInstant = getField(plugin, "lastHydrateInstant");
        assertEquals(now, resetInstant);
    }

    @Test
    public void testResetHydrateReminderTimeInterval()
    {
        final Instant now = getField(plugin, "lastHydrateInstant");
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
    public void testGetHydrateReminderMessage(@Mocked PlayerImpl player)
    {
        final String playerName = "OSRSplayer42";
        new Expectations(player)
        {{
            client.getLocalPlayer();
            result = player;
            times = 1;
            player.getName();
            result = playerName;
            times = 1;
        }};
        final List<String> hydrateMessageList = getField(plugin, "HYDRATE_BREAK_TEXT_LIST");
        final String message = invoke(plugin, "getHydrateReminderMessage");
        assertTrue(hydrateMessageList.contains(message.replace(", OSRSplayer42", "")));
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
            client.addChatMessage(messageType, "",message, "");
            times = 1;
        }};
        invoke(plugin, "sendHydrateReminderChatMessage", message);
    }

    @Test
    public void testSendHydrateReminderClanChatMessage()
    {
        final ChatMessageType messageType = ChatMessageType.FRIENDSCHAT;
        final String message = "Hydrate now";
        final String username = getField(plugin, "HYDRATE_REMINDER_USERNAME");
        new Expectations(plugin) {{
            invoke(plugin, "getChatNotificationMessageType");
            result = messageType;
            times = 1;
            client.addChatMessage(messageType, "",message, username);
            times = 1;
        }};
        invoke(plugin, "sendHydrateReminderChatMessage", message);
    }

    @Test
    public void testSendHydrateReminderNotification()
    {
        final String message = "Break to Hydrate";
        new Expectations(plugin) {{
            notifier.notify(message);
            times = 1;
        }};
        invoke(plugin, "sendHydrateReminderNotification", message);
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
