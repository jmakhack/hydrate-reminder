package com.hydratereminder.command;

import com.hydratereminder.HydrateReminderCommandArgs;
import com.hydratereminder.chat.ChatMessageSender;
import com.hydratereminder.command.help.HelpCommand;
import com.hydratereminder.command.hydrate.HydrateCommand;
import net.runelite.api.events.CommandExecuted;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.mock;

/**
 * <p>The unit tests for the command invoker logic
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class CommandInvokerTest
{

    /**
     * Mock chat message sender
     */
    @Mock
    private transient ChatMessageSender chatMessageSender;

    /**
     * Mock command creator
     */
    @Mock
    private transient CommandCreator commandCreator;

    /**
     * Mock command invoker
     */
    @InjectMocks
    private transient CommandInvoker commandInvoker;

    /**
     * <p>Tests that the command creator gets called only once when a
     * command has been executed successfully
     * </p>
     */
    @Test
    /* default */ void shouldCallCommandCreatorOnlyOnceWhenCommandWasExecutedProperly()
    {
        final HydrateReminderCommandArgs commandArgs = HydrateReminderCommandArgs.HYDRATE;
        final Command hydrateCommand = mock(HydrateCommand.class);
        final CommandExecuted commandToExecute = new CommandExecuted("hr", new String[]{"hydrate"});
        given(commandCreator.createFrom(commandArgs)).willReturn(hydrateCommand);
        commandInvoker.invokeCommand(commandToExecute);
        verify(commandCreator, times(1)).createFrom(any());
    }

    /**
     * <p>Tests that the proper message is generated and displayed when a
     * not recognized command exception is thrown
     * </p>
     */
    @Test
    /* default */ void shouldSendProperMessageWhenNotRecognizedCommandExceptionIsThrown()
    {
        final String expectedExceptionMessage = new NotRecognizedCommandException("wrong").getMessage();
        final Command helpCommand = mock(HelpCommand.class);
        final CommandExecuted commandToExecute = new CommandExecuted("hr", new String[]{"wrong"});
        given(commandCreator.createFrom(HydrateReminderCommandArgs.HELP)).willReturn(helpCommand);
        commandInvoker.invokeCommand(commandToExecute);
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedExceptionMessage);
        verify(commandCreator).createFrom(HydrateReminderCommandArgs.HELP);
    }

    /**
     * <p>Tests that the command creator is called twice whenever a not
     * supported command exception is thrown
     * </p>
     */
    @Test
    /* default */ void shouldCallCommandCreatorTwiceWhenNotSupportedCommandExceptionIsThrown()
    {
        final HydrateReminderCommandArgs commandArgs = HydrateReminderCommandArgs.HYDRATE;
        final CommandExecuted commandToExecute = new CommandExecuted("hr", new String[]{"hydrate"});
        final Command helpCommand = mock(HelpCommand.class);
        given(commandCreator.createFrom(commandArgs)).willThrow(NotSupportedCommandException.class);
        given(commandCreator.createFrom(HydrateReminderCommandArgs.HELP)).willReturn(helpCommand);
        commandInvoker.invokeCommand(commandToExecute);
        verify(commandCreator).createFrom(commandArgs);
        verify(commandCreator).createFrom(HydrateReminderCommandArgs.HELP);
    }

    /**
     * <p>Tests that the proper message is generated and displayed when a
     * not recognized command exception is thrown
     * </p>
     */
    @Test
    /* default */ void shouldReturnNothingWhenIsNotHydrateCommand()
    {
        final CommandExecuted commandToExecute = new CommandExecuted("aa", new String[]{"hydrate"});
        commandInvoker.invokeCommand(commandToExecute);
        verify(commandCreator, never()).createFrom(any());
    }
}
