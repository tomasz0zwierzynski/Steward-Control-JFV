package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;

public class CommandExecutor implements CommandOutputQueuer, CommandInputQueuer {

    @Override
    public void addInputCommandToQueue(AbstractCommand cmd) {
        try {
            cmd.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}