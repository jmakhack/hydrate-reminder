package com.hydratereminder.command.total;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

/**
 * <p> This class writes and reads a file related to total hydration
 * breaks for all time
 * </p>
 */
@Slf4j
public class TotalBreakWriter {
    /**
     * <p>Directory for Hydrate Reminder</p>
     */
    private static final File HYDRATION_REMINDER_DIR = new File(RUNELITE_DIR, "hydrateReminder");

    /**
     * <p>File for total hydration breaks</p>
     */
    private static final File HYDRATION_REMINDER_BREAKS_FILE =
            new File(HYDRATION_REMINDER_DIR, "totalHydrationBreaks.json");

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
     * <p>Gets String of bytes from hydration breaks file
     * </p>
     * @return string of bytes
     * @throws IOException
     */
    private static String getFileContent() throws IOException
    {
        Path filePath = Paths.get(HYDRATION_REMINDER_BREAKS_FILE.toString());
        byte[] fileBytes = Files.readAllBytes(filePath);
        return new String(fileBytes);
    }

    /**
     * <p>Loads the total hydration break file if it exists and 0 if not
     * </p>
     * @return total hydration breaks for all sessions
     */
    public int loadTotalBreakFile()
    {
        int totalBreaks = 0;
        synchronized (this)
        {
            try
            {
                Map<String, String> map = new HashMap<>();
                final Gson gson = new Gson();
                String jsonString = getFileContent();
                Map<String, String> data = gson.fromJson(jsonString, map.getClass());
                totalBreaks = Integer.parseInt(data.get("totalHydrateCount"));
            }
            catch (IOException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn("IOException for file {}: {}", HYDRATION_REMINDER_BREAKS_FILE, e.getMessage());
                }
            }
        }

        return totalBreaks;
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
            try
            {
                Map<String, String> data = new HashMap<>();
                data.put("totalHydrateCount", String.valueOf(totalHydrationBreaks));

                final Gson gson = new Gson();
                final String json = gson.toJson(data);
                Files.write(HYDRATION_REMINDER_BREAKS_FILE.toPath(), json.getBytes());
            }
            catch (IOException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn("IOException for file {}: {}", HYDRATION_REMINDER_BREAKS_FILE, e.getMessage());
                }
            }
        }
    }
}
