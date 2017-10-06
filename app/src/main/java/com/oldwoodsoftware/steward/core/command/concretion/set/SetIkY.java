package com.oldwoodsoftware.steward.core.command.concretion.set;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractSetCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class SetIkY extends AbstractSetCommand{
    public SetIkY(PlatformContext pContext, float value,boolean incoming){
        super(pContext,incoming);
        this.value = value;
    }
    @Override
    public void execute() throws Exception {
        if(incoming){

        }else{
            String msg = CommandType.setIkY.get_uC_command_code_as_string() + "=" + String.valueOf(value);
            btConnection.sendMessage(msg.getBytes());
        }
    }
}
