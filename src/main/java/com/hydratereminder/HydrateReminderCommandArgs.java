/*
 * Copyright (c) 2021, jmakhack <https://github.com/jmakhack>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.hydratereminder;

import com.hydratereminder.command.NotRecognizedCommandException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>All command arguments that the Hydrate Reminder plugin supports
 * </p>
 * @author jmakhack
 */
@Getter
@AllArgsConstructor
public enum HydrateReminderCommandArgs
{
    NEXT("next", "n"),
    PREV("prev", "p"),
    RESET("reset", "r"),
    HYDRATE("hydrate","hr"),
    HELP("help", "h"),
    TOTAL("total", "t");

    /**
     * Command argument name
     */
    private final String commandArg;
    private final String commandArgAbbr;

    /**
     * <p>Get the command argument name as a String
     * </p>
     * @return command argument name
     * @since 1.1.0
     */
    @Override
    public String toString()
    {
        return getCommandArg();
    }

    /**
     * <p>Get the enum value from a given command string
     * </p>
     * @param command chat command to get value for
     * @since 1.1.0
     */
    public static HydrateReminderCommandArgs getValue(String command)
    {
        for (HydrateReminderCommandArgs enumValue : HydrateReminderCommandArgs.values())
        {
            if (Objects.equals(enumValue.getCommandArg(), command) || Objects.equals(enumValue.getCommandArgAbbr(), command))
            {
                return enumValue;
            }
        }
        throw new NotRecognizedCommandException(command);
    }
}
