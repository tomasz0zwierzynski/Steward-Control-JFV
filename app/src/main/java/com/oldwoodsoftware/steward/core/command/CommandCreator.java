package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;

public interface CommandCreator {
    public AbstractCommand createCommand(CommandType cmdType, float value, boolean incoming);
    public AbstractCommand createCommand(String cmd, boolean incoming);
}
