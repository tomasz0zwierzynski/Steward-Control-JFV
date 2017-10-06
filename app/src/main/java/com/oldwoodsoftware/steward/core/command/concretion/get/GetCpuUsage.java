package com.oldwoodsoftware.steward.core.command.concretion.get;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractGetCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class GetCpuUsage extends AbstractGetCommand{

    public GetCpuUsage(PlatformContext pContext, float value, boolean incoming){
        super(pContext,incoming);
        this.value = value;
    }

    @Override
    public void execute() throws Exception{
        if(incoming){
            pContext.getGeneralSystem().setCpuUsage(value);
        }else{
            String msg = CommandType.getCpuUsage.get_uC_command_code_as_string() + "=";
            btConnection.sendMessage(msg.getBytes());
        }
    }
}
