package com.oldwoodsoftware.steward.core.command.concretion.set;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractSetCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class SetSetpointX extends AbstractSetCommand{
    public SetSetpointX(PlatformContext pContext, float value, boolean incoming){
        super(pContext,incoming);
        this.value = value;
    }
    @Override
    public void execute() throws Exception {
        logExecution();
        if(incoming){

        }else{
            String msg = CommandType.setSetpointX.get_uC_command_code_as_string() + "=" + String.valueOf(value);
            btConnection.sendMessage(msg.getBytes());
            pContext.getPidControlX().setSetpoint(value);
        }
    }
}
