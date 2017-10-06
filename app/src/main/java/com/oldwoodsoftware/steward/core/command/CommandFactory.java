package com.oldwoodsoftware.steward.core.command;

import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.concretion.Empty;
import com.oldwoodsoftware.steward.core.command.concretion.MoveTo;
import com.oldwoodsoftware.steward.core.command.concretion.StartMode;
import com.oldwoodsoftware.steward.core.command.concretion.Submit;
import com.oldwoodsoftware.steward.core.command.concretion.get.GetCpuUsage;
import com.oldwoodsoftware.steward.core.command.concretion.get.GetFreeHeap;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetIkPitch;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetIkRoll;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetIkX;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetIkY;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetIkYaw;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetIkZ;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetMode;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetSetpointX;
import com.oldwoodsoftware.steward.core.command.concretion.set.SetSetpointY;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

public class CommandFactory implements CommandCreator{
    private PlatformContext pContext;

    public CommandFactory(PlatformContext platformContext){
        pContext = platformContext;
    }

    @Override
    public AbstractCommand createCommand(CommandType cmdType, float value, boolean incoming) {
        AbstractCommand acCommand = new Empty(pContext,incoming);
        switch (cmdType){
            case empty:
                break;
            case fail:
                break;
            case ok:
                break;
            case submit:
                System.out.println("Debug: CreateCommand():submit");
                acCommand = new Submit(pContext,incoming);
                break;
            case cancel:
                break;
            case startMode:
                System.out.println("Debug: CreateCommand():startMode");
                acCommand = new StartMode(pContext,incoming);
                break;
            case stopMode:
                break;
            case resetMode:
                break;
            case moveTo:
                System.out.println("Debug: CreateCommand():moveTo");
                acCommand = new MoveTo(pContext,incoming);
                break;
            case selectPid:
                break;
            case setMode:
                System.out.println("Debug: CreateCommand():setMode");
                acCommand = new SetMode(pContext, PlatformMode.getMode((int)value),incoming);
                //new Exception().printStackTrace();
                break;
            case setSetpointX:
                System.out.println("Debug: CreateCommand():setSetpointX");
                acCommand = new SetSetpointX(pContext, value, incoming);
                break;
            case setSetpointY:
                System.out.println("Debug: CreateCommand():setSetpointY");
                acCommand = new SetSetpointY(pContext, value, incoming);
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
                acCommand = new SetIkX(pContext,value, incoming);
                break;
            case setIkY:
                acCommand = new SetIkY(pContext,value, incoming);
                break;
            case setIkZ:
                acCommand = new SetIkZ(pContext,value, incoming);
                break;
            case setIkRoll:
                acCommand = new SetIkRoll(pContext,value, incoming);
                break;
            case setIkPitch:
                acCommand = new SetIkPitch(pContext,value, incoming);
                break;
            case setIkYaw:
                acCommand = new SetIkYaw(pContext,value, incoming);
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
                System.out.println("Debug: CreateCommand():getFreeHeap");
                acCommand = new GetFreeHeap(pContext,value,incoming);
                break;
            case getCpuUsage:
                System.out.println("Debug: CreateCommand():getCpuUsage");
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

    /*
    @Override
    public AbstractCommand createCommand(CommandType cmdType, float value) {
        return createCommand(cmdType, value, false);
    }

    @Override
    public AbstractCommand createCommand(String cmd) {
        return createCommand(cmd, false);
    }
    */

}
