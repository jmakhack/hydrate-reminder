package com.hydratereminder.command.total;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static net.runelite.client.RuneLite.RUNELITE_DIR;

@Slf4j
public class TotalBreakWriter {
    private static final String FILE_EXTENSION = ".log";
    private static final File HYDRATION_REMINDER_DIR = new File(RUNELITE_DIR, "hydrateReminder");
    private int totalBreaks = 0;

    @Inject
    public TotalBreakWriter()
    {
        if (!HYDRATION_REMINDER_DIR.exists())
        {
            boolean mkDir = HYDRATION_REMINDER_DIR.mkdir();

            if(!mkDir)
                log.warn("Directory creation failed");
        }
    }


    public synchronized int loadTotalBreakFile()
    {
        final String totalBreakFileName = "totalHydrationBreaks" + FILE_EXTENSION;
        final File totalBreakFile = new File (HYDRATION_REMINDER_DIR, totalBreakFileName);

        if (!totalBreakFile.exists())
            return 0;

        try (final BufferedReader reader = new BufferedReader(new FileReader(totalBreakFile)))
        {
                totalBreaks += Integer.parseInt(reader.readLine());
        }
        catch (IOException e)
        {
            log.warn("IOException for file {}: {}", totalBreakFile, e.getMessage());
        }

        return totalBreaks;
    }

    public synchronized void writeTotalBreakFile(final int totalHydrationBreaks)
    {
        final File breakFile = new File(HYDRATION_REMINDER_DIR, "totalHydrationBreaks" + FILE_EXTENSION);

        try
        {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(breakFile), false));

            final String breaksAsString = String.valueOf(totalHydrationBreaks);
            writer.append(breaksAsString);

            writer.close();
        }
        catch (IOException e)
        {
            log.warn("IOException for file {}: {}", breakFile, e.getMessage());
        }
    }
}
