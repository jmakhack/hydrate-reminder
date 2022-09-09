package com.hydratereminder.command.hydrate;

import com.hydratereminder.command.Command;

public class HydrateCommand implements Command {

    private final HydrateCommandHandler hydrateCommandHandler;

    public HydrateCommand(HydrateCommandHandler hydrateCommandHandler) {
        this.hydrateCommandHandler = hydrateCommandHandler;
    }

    @Override
    public void execute() {
        hydrateCommandHandler.handle();
    }

}
