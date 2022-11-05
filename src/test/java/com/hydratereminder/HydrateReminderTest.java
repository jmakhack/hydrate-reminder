package com.hydratereminder;

import lombok.experimental.UtilityClass;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

/**
 * <p>Main class to run project
 * </p>
 */
@UtilityClass
public class HydrateReminderTest
{

	/**
	 * Main method that starts RuneLite client with plugin code
	 * @param args program arguments
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HydrateReminderPlugin.class);
		RuneLite.main(args);
	}
}
