package com.hydratereminder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HydrateReminderPersonalityType
{
    SIMPLE("Simple"),
    FUN("Fun"),
    CARING("Caring");

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
