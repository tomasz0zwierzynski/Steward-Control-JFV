package com.oldwoodsoftware.steward.core.command.concretion.set;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractSetCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class SetSetpointY extends AbstractSetCommand{
    public SetSetpointY(PlatformContext pContext, float value,boolean incoming){
        super(pContext,incoming);
        this.value = value;
    }
    @Override
    public void execute() throws Exception {
        if(incoming){

        }else{
            System.out.println("Debug: SetSetpointY.execute()");
            String msg = CommandType.setSetpointY.get_uC_command_code_as_string() + "=" + String.valueOf(value);
            btConnection.sendMessage(msg.getBytes());
        }
    }
}