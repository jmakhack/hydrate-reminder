package com.hydratereminder.command.next;

import com.hydratereminder.command.Command;

public class NextCommand implements Command {

    private final transient NextCommandHandler nextCommandHandler;

    public NextCommand(NextCommandHandler nextCommandHandler) {
        this.nextCommandHandler = nextCommandHandler;
    }

    @Override
    public void execute() {
        nextCommandHandler.handle();
    }

}
