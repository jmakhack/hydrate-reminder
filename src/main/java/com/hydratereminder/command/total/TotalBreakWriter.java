package com.hydratereminder.command.total;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

@Slf4j
public class TotalBreakWriter {
    /**
     * <p>Directory for Hydrate Reminder</p>
     */
    private static final File HYDRATION_REMINDER_DIR = new File(RUNELITE_DIR, "hydrateReminder");

    /**
     * <p>File for total hydration breaks</p>
     */
    private static final String HYDRATION_REMINDER_BREAKS_FILE_NAME =
            new File(HYDRATION_REMINDER_DIR, "totalHydrationBreaks.log").toString();

    /**
     * <p>Total number of breaks of all time</p>
     */
    private transient int totalBreaks;

    /**
     * <p> Upon initialization, creates a directory in the RuneLite directory
     * for total hydration breaks.
     * </p>
     */
    @Inject
    public TotalBreakWriter()
    {
        if (!HYDRATION_REMINDER_DIR.exists()) {
            final boolean mkDir = HYDRATION_REMINDER_DIR.mkdir();

            if (!mkDir)
            {
                log.warn("Directory creation failed");
            }
        }
    }

    /**
     * <p>Loads the total hydration break file if it exists and 0 if not
     * </p>
     * @return total hydration breaks for all sessions
     */
    public int loadTotalBreakFile()
    {
        synchronized (this)
        {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(HYDRATION_REMINDER_BREAKS_FILE_NAME)))
            {
                totalBreaks = Integer.parseInt(reader.readLine());
            }
            catch (IOException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn("IOException for file {}: {}", HYDRATION_REMINDER_BREAKS_FILE_NAME, e.getMessage());
                }
            }

            return totalBreaks;
        }
    }

    /**
     * <p> Updates the total hydration break file with the current session
     * hydration breaks
     * </p>
     * @param totalHydrationBreaks current session hydration breaks
     */
    public void writeTotalBreakFile(final int totalHydrationBreaks)
    {
        synchronized (this)
        {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(HYDRATION_REMINDER_BREAKS_FILE_NAME)))
            {
                final String breaksAsString = String.valueOf(totalHydrationBreaks);
                writer.append(breaksAsString);
            }
            catch (IOException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn("IOException for file {}: {}", HYDRATION_REMINDER_BREAKS_FILE_NAME, e.getMessage());
                }
            }
        }
    }
}
