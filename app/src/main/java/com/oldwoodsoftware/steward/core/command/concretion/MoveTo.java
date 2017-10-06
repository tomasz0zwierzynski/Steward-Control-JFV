package com.oldwoodsoftware.steward.core.command.concretion;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class MoveTo extends AbstractCommand{

    public MoveTo(PlatformContext pContext,boolean incoming) {
        super(pContext,incoming);
    }

    @Override
    public void execute() throws Exception {
        if (incoming){

        }else{
            System.out.println("Debug: MoveTo.execute()");
            String msg = CommandType.moveTo.get_uC_command_code_as_string() + "=";
            btConnection.sendMessage(msg.getBytes());
            pContext.getStateMachine().setMoveTo(true);
        }
    }
}
