package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;

public interface CommandInputQueuer {
    void addInputCommandToQueue(AbstractCommand cmd);
}
