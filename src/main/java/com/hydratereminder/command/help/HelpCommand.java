package com.hydratereminder.command.help;

import com.hydratereminder.command.Command;

public class HelpCommand implements Command {

    private final transient HelpCommandHandler helpCommandHandler;

    public HelpCommand(HelpCommandHandler helpCommandHandler) {
        this.helpCommandHandler = helpCommandHandler;
    }

    @Override
    public void execute() {
        helpCommandHandler.handle();
    }

}
