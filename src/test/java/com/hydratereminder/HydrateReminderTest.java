package com.hydratereminder;

import lombok.experimental.UtilityClass;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

@UtilityClass
public class HydrateReminderTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HydrateReminderPlugin.class);
		RuneLite.main(args);
	}
}
