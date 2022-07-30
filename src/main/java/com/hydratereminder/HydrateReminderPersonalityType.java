package com.hydratereminder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum HydrateReminderPersonalityType
{
    STRAIGHTFORWARD("Straightforward"),
    FUN("Fun");

    private final String personalityType;

    /**
     * <p>Get the personality type as a String
     * </p>
     * @return personality type
     * @since 2.0.0
     */
    @Override
    public String toString()
    {
        return getPersonalityType();
    }
}
