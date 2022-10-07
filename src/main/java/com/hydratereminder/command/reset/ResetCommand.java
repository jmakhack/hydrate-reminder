package com.hydratereminder.command.reset;

import com.hydratereminder.command.Command;

public class ResetCommand implements Command {

    private final transient ResetCommandHandler resetCommandHandler;

    public ResetCommand(ResetCommandHandler resetCommandHandler) {
        this.resetCommandHandler = resetCommandHandler;
    }

    @Override
    public void execute() {
        resetCommandHandler.handle();
    }

}
