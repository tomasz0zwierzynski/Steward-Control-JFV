package com.oldwoodsoftware.steward.core.command.concretion.set;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractSetCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

public class SetMode extends AbstractSetCommand{
    private PlatformMode platformMode;

    public SetMode(PlatformContext pContext, PlatformMode pMode, boolean incoming){
        super(pContext,incoming);
        platformMode = pMode;
    }

    @Override
    public void execute() throws Exception {
        logExecution();
        if(incoming){

        }else{
            String msg = CommandType.setMode.get_uC_command_code_as_string() + "=" + platformMode.get_uC_mode_as_string();
            btConnection.sendMessage(msg.getBytes());
            pContext.getStateMachine().setMode(platformMode);
        }
    }
}
