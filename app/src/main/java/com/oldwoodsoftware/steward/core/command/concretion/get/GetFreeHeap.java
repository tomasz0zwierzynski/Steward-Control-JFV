package com.oldwoodsoftware.steward.core.command.concretion.get;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractGetCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class GetFreeHeap extends AbstractGetCommand{

    public GetFreeHeap(PlatformContext pContext, float value, boolean incoming){
        super(pContext,incoming);
        this.value = value;
    }

    @Override
    public void execute() throws Exception{
        if(incoming){
            pContext.getGeneralSystem().setFreeHeap((int)value);
        }else{
            String msg = CommandType.getFreeHeap.get_uC_command_code_as_string() + "=";
            btConnection.sendMessage(msg.getBytes());
        }
    }
}
