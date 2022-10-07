package com.hydratereminder.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.hydratereminder.HydrateReminderCommandArgs;
import com.hydratereminder.command.help.HelpCommand;
import com.hydratereminder.command.help.HelpCommandHandler;
import com.hydratereminder.command.hydrate.HydrateCommand;
import com.hydratereminder.command.hydrate.HydrateCommandHandler;
import com.hydratereminder.command.next.NextCommand;
import com.hydratereminder.command.next.NextCommandHandler;
import com.hydratereminder.command.prev.PrevCommand;
import com.hydratereminder.command.prev.PrevCommandHandler;
import com.hydratereminder.command.reset.ResetCommand;
import com.hydratereminder.command.reset.ResetCommandHandler;
import com.hydratereminder.command.total.TotalCommand;
import com.hydratereminder.command.total.TotalCommandHandler;

@Singleton
class CommandCreator {

    @Inject
    private transient NextCommandHandler nextCommandHandler;
    @Inject
    private transient PrevCommandHandler prevCommandHandler;
    @Inject
    private transient esetCommandHandler resetCommandHandler;
    @Inject
    private transient HelpCommandHandler helpCommandHandler;
    @Inject
    private transient TotalCommandHandler totalCommandHandler;
    @Inject
    private transient HydrateCommandHandler hydrateCommandHandler;

    Command createFrom(HydrateReminderCommandArgs commandType) {
        switch (commandType) {
            case NEXT:
                return new NextCommand(nextCommandHandler);
            case PREV:
                return new PrevCommand(prevCommandHandler);
            case RESET:
                return new ResetCommand(resetCommandHandler);
            case HYDRATE:
                return new HydrateCommand(hydrateCommandHandler);
            case HELP:
                return new HelpCommand(helpCommandHandler);
            case TOTAL:
                return new TotalCommand(totalCommandHandler);
            default:
                throw new NotSupportedCommandException(commandType);
        }

    }

}
