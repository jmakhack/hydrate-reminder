package com.hydratereminder.dictionary;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HydrateWelcomeMessageDictionary {

    /**
     * Hydrate Reminder startup welcome text to display
     */
    private static final List<String> HYDRATE_WELCOME_TEXT_LIST =
            Collections.unmodifiableList(
                    new ArrayList<String>() {{
                        add("Don't forget to stay hydrated.");
                        add("Type \"::hydrate help\" in chat to view available commands.");
                        add("Stay cool. Stay awesome. Stay hydrated.");
                        add("Keep calm and stay hydrated.");
                        add("Cheers to staying hydrated!");
                        add("Keep the geyser titans happy by staying hydrated.");
                        add("Hydration is love. Hydration is life.");
                        add("Out of water? Cast humidify to stay hydrated.");
                        add("It costs zero water runes to stay hydrated.");
                        add("Check out the hydrate commands by typing \"::hydrate help\" in chat.");
                        add("A hydrated adventurer is an unstoppable adventurer.");
                        add("It's dangerous to go alone. Stay hydrated!");
                        add("Welcome traveler. Nothing hurts morale like dehydration! Remember to drink water.");
                        add("People who don't believe in magic have obviously never had water!");
                        add("You're 70% water. Don't forget to stay hydrated!");
                        add("\"Thousands have lived without love, not one without water.\" - W.J. Auden");
                        add("Having trouble focusing? Sounds like you need to drink some water ;)");
                        add("\"If there is magic on this planet, it is contained in water.\" - Loren Eiseley");
                        add("Feeling low on energy? Drink some water!");
                        add("Nothing like RuneScape and a tall glass of ice cold water!");
                        add("I suppose we'll allow tea... For now.");
                        add("Type \"::hydrate next\" to view the time remaining until the next hydration break!");
                        add("Save key strokes by using the short hand \"::hr <command>\" instead of \"::hydrate <command>\"");
                        add("Imagine getting in a sword fight while dehydrated. What. A. Nightmare.");
                        add("Don't forget to stay hydrated while out and about!");
                        add("A wise traveler is a hydrated traveler.");
                        add("Remember to drink plenty of water!");
                        add("I once started a quest without adequate water. NEVER AGAIN.");
                        add("Welcome! Stay hydrated out there!");
                        add("Welcome traveler. Would it be rude to say you look a little thirsty?");
                        add("\"Thousands have lived without love, not one without water\". ― W. H. Auden");
                        add("It's possible to decant water into other containers, resulting in MORE HYDRATION!!!");
                        add("Aaah, nothing like a nice skinna water!");
                        add("It's possible to live several days without water, but not with this plugin");
                    }});

    public static String getRandomWelcomeMessage() {
        final SecureRandom randomGenerator = new SecureRandom();
        final int randomNumber = randomGenerator.nextInt(HYDRATE_WELCOME_TEXT_LIST.size());
        return HYDRATE_WELCOME_TEXT_LIST.get(randomNumber);
    }

}
