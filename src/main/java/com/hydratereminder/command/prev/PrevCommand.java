package com.hydratereminder.command.prev;

import com.hydratereminder.command.Command;

public class PrevCommand implements Command {

    private final transient PrevCommandHandler prevCommandHandler;

    public PrevCommand(PrevCommandHandler prevCommandHandler) {
        this.prevCommandHandler = prevCommandHandler;
    }

    @Override
    public void execute() {
        prevCommandHandler.handle();
    }

}
