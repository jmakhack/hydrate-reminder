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
    private static final String FILE_EXTENSION = ".log";
    private static final File HYDRATION_REMINDER_DIR = new File(RUNELITE_DIR, "hydrateReminder");
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
            final String totalBreakFileName = "totalHydrationBreaks" + FILE_EXTENSION;
            final File totalBreakFile = new File (HYDRATION_REMINDER_DIR, totalBreakFileName);

            try (BufferedReader reader = Files.newBufferedReader(Paths.get(totalBreakFileName)))
            {
                totalBreaks += Integer.parseInt(reader.readLine());
            }
            catch (IOException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn("IOException for file {}: {}", totalBreakFile, e.getMessage());
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
            final File breakFile = new File(HYDRATION_REMINDER_DIR, "totalHydrationBreaks" + FILE_EXTENSION);

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("totalHydrationBreaks.log")))
            {
                final String breaksAsString = String.valueOf(totalHydrationBreaks);
                writer.append(breaksAsString);
            }
            catch (IOException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn("IOException for file {}: {}", breakFile, e.getMessage());
                }
            }
        }
    }
}
