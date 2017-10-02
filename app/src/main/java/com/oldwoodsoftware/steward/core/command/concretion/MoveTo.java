package com.oldwoodsoftware.steward.core.command.concretion;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;

public class MoveTo extends AbstractCommand{

    @Override
    public void execute() throws Exception {
        String msg = CommandType.moveTo.get_uC_command_code_as_string() + "=";
        btConnection.sendMessage(msg.getBytes());
    }
}
