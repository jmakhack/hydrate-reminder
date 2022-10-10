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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {

    @Mock
    private transient ChatMessageSender chatMessageSender;
    @Mock
    private transient CommandCreator commandCreator;
    @InjectMocks
    private transient CommandInvoker commandInvoker;

    @Test
    public void shouldCallCommandCreatorOnlyOnceWhenCommandWasExecutedProperly() {
        // given
        HydrateReminderCommandArgs commandArgs = HydrateReminderCommandArgs.HYDRATE;
        Command hydrateCommand = Mockito.mock(HydrateCommand.class);
        CommandExecuted commandToExecute = new CommandExecuted("hr", new String[]{"hydrate"});

        given(commandCreator.createFrom(commandArgs)).willReturn(hydrateCommand);

        // when
        commandInvoker.invokeCommand(commandToExecute);

        // then
        verify(commandCreator, times(1)).createFrom(any());
    }

    @Test
    public void shouldSendProperMessageWhenNotRecognizedCommandExceptionIsThrown() {
        // given
        String expectedExceptionMessage = new NotRecognizedCommandException("wrong").getReason();
        Command helpCommand = Mockito.mock(HelpCommand.class);
        CommandExecuted commandToExecute = new CommandExecuted("hr", new String[]{"wrong"});

        given(commandCreator.createFrom(HydrateReminderCommandArgs.HELP)).willReturn(helpCommand);

        // when
        commandInvoker.invokeCommand(commandToExecute);

        // then
        verify(chatMessageSender).sendHydrateEmojiChatGameMessage(expectedExceptionMessage);
        verify(commandCreator).createFrom(HydrateReminderCommandArgs.HELP);
    }

    @Test
    public void shouldCallCommandCreatorTwiceWhenNotSupportedCommandExceptionIsThrown() {
        // given
        HydrateReminderCommandArgs commandArgs = HydrateReminderCommandArgs.HYDRATE;
        CommandExecuted commandToExecute = new CommandExecuted("hr", new String[]{"hydrate"});
        Command helpCommand = Mockito.mock(HelpCommand.class);

        given(commandCreator.createFrom(commandArgs)).willThrow(NotSupportedCommandException.class);
        given(commandCreator.createFrom(HydrateReminderCommandArgs.HELP)).willReturn(helpCommand);

        // when
        commandInvoker.invokeCommand(commandToExecute);

        // then
        verify(commandCreator).createFrom(commandArgs);
        verify(commandCreator).createFrom(HydrateReminderCommandArgs.HELP);
    }

    @Test
    public void shouldReturnNothingWhenIsNotHydrateCommand() {
        // given
        CommandExecuted commandToExecute = new CommandExecuted("aa", new String[]{"hydrate"});

        // when and then
        commandInvoker.invokeCommand(commandToExecute);
        verify(commandCreator,org.mockito.Mockito.never()).createFrom(any());

    }

}

