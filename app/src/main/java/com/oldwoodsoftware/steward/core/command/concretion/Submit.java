package com.oldwoodsoftware.steward.core.command.concretion;

import android.util.Log;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class Submit extends AbstractCommand{
    public Submit(PlatformContext pContext,boolean incoming) {
        super(pContext,incoming);

    }

    @Override
    public void execute() throws Exception {
        logExecution();
        if (incoming){

        }else {
            String msg = CommandType.submit.get_uC_command_code_as_string() + "=";
            btConnection.sendMessage(msg.getBytes());
            pContext.getStateMachine().setMoveTo(false);
        }
    }
}
