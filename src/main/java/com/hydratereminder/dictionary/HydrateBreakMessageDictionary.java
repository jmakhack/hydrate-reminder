package com.hydratereminder.dictionary;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hydratereminder.HydrateReminderPersonalityType;

public class HydrateBreakMessageDictionary {

    /**
     * Hydrate Reminder interval break text to display in straightforward form
     */
    private static final List<String> HYDRATE_BREAK_STRAIGHTFORWARD_TEXT_LIST =
            Collections.unmodifiableList(
                    new ArrayList<String>() {{
                        add("It's time for a quick hydration break");
                    }}
            );
    /**
     * Hydrate Reminder interval break text to display in funny form
     */
    private static final List<String> HYDRATE_BREAK_FUNNY_TEXT_LIST =
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
                        add("3... 2... 1... It's hydration time");
                        add("Feeling parched yet? It's hydration time");
                        add("Now would be a fantastic time to hydrate");
                        add("Water... you need... water");
                        add("Thirsty? Time to grab a drink");
                        add("Water: a liquid that is necessary for life. Why don't you drink some");
                        add("Hello, this is your reminder to take a hydration break");
                        add("Remember to stay hydrated");
                        add("What'cha drinking there? It's time to take another sip");
                        add("Ding ding ding! Hydration time");
                        add("Everyone needs water, you should drink some now");
                        add("Time for another glass of water");
                        add("You've been grinding hard, time to reward your self with a hydration break");
                        add("Water makes the world go 'round, you should drink some now");
                        add("Hydration can improve your ability to focus, time for a hydration break");
                        add("Time to take a break and hydrate");
                        add("Dehydration can cause you to feel dizzy and lightheaded, take a hydration break");
                        add("Dehydration can cause dry mouth, lips, and eyes. Take a hydration break");
                    }}
            );

    public static String getRandomHydrateBreakMessageForPersonality(HydrateReminderPersonalityType personalityType)
    {
        String breakMessage;
        switch (personalityType)
        {
            case STRAIGHTFORWARD:
                breakMessage = HYDRATE_BREAK_STRAIGHTFORWARD_TEXT_LIST.get(0);
                break;
            case FUN:
                final SecureRandom randomGenerator = new SecureRandom();
                final int randomNumber = randomGenerator.nextInt(HYDRATE_BREAK_FUNNY_TEXT_LIST.size());
                breakMessage = HYDRATE_BREAK_FUNNY_TEXT_LIST.get(randomNumber);
                break;
            default:
                throw new IllegalStateException("Provided personality type is not supported");
        }
        return breakMessage;
    }

}
