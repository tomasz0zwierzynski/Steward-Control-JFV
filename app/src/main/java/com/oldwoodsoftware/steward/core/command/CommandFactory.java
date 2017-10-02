package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.concretion.Empty;
import com.oldwoodsoftware.steward.core.command.concretion.get.GetCpuUsage;
import com.oldwoodsoftware.steward.core.command.concretion.get.GetFreeHeap;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;

public class CommandFactory implements CommandCreator{
    private PlatformContext pContext;

    public CommandFactory(PlatformContext platformContext){
        pContext = platformContext;
    }

    @Override
    public AbstractCommand createCommand(CommandType cmdType, float value, boolean incoming) {
        AbstractCommand acCommand = new Empty();
        switch (cmdType){

            case empty:
                break;
            case fail:
                break;
            case ok:
                break;
            case submit:
                break;
            case cancel:
                break;
            case startMode:
                break;
            case stopMode:
                break;
            case resetMode:
                break;
            case moveTo:
                break;
            case selectPid:
                break;
            case setMode:
                break;
            case setSetpointX:
                break;
            case setSetpointY:
                break;
            case setPidSamplingInterval:
                break;
            case setKp:
                break;
            case setKi:
                break;
            case setKd:
                break;
            case setN:
                break;
            case setPidUpperLimit:
                break;
            case setPidLowerLimit:
                break;
            case setPidDeadband:
                break;
            case setIkX:
                break;
            case setIkY:
                break;
            case setIkZ:
                break;
            case setIkRoll:
                break;
            case setIkPitch:
                break;
            case setIkYaw:
                break;
            case isPanelTouched:
                break;
            case getPanelX:
                break;
            case getPanelY:
                break;
            case getMode:
                break;
            case isModeWorking:
                break;
            case getSetpointX:
                break;
            case getSetpointY:
                break;
            case getErrorX:
                break;
            case getErrorY:
                break;
            case getPidSamplingInterval:
                break;
            case getPid:
                break;
            case getKp:
                break;
            case getKi:
                break;
            case getKd:
                break;
            case getN:
                break;
            case getPidUpperLimit:
                break;
            case getPidLowerLimit:
                break;
            case getPidDeadband:
                break;
            case isPidWorking:
                break;
            case getIkX:
                break;
            case getIkY:
                break;
            case getIkZ:
                break;
            case getIkRoll:
                break;
            case getIkPitch:
                break;
            case getIkYaw:
                break;
            case getFreeHeap:
                acCommand = new GetFreeHeap(pContext,value,incoming);
                break;
            case getCpuUsage:
                acCommand = new GetCpuUsage(pContext,value,incoming);
                break;
        }
        return acCommand;
    }

    @Override
    public AbstractCommand createCommand(String cmd, boolean incoming) {
        String sCommand = cmd.substring(0, cmd.indexOf('='));
        String sValue = cmd.substring(cmd.indexOf('=')+1, cmd.length());

        int command = Integer.parseInt(sCommand);
        CommandType cmdType = CommandType.getCommandType(command);
        float value = Float.parseFloat(sValue);

        return createCommand(cmdType,value,incoming);
    }


}
