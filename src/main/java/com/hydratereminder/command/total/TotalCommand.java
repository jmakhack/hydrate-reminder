package com.hydratereminder.command.total;

import com.hydratereminder.command.Command;

public class TotalCommand implements Command {

    private final TotalCommandHandler totalCommandHandler;

    public TotalCommand(TotalCommandHandler totalCommandHandler) {
        this.totalCommandHandler = totalCommandHandler;
    }

    @Override
    public void execute() {
        totalCommandHandler.handle();
    }

}
