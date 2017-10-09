package com.oldwoodsoftware.steward.core.command;

import android.util.Log;

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
                Log.i("CommandFactory","CreateCommand():submit");
                acCommand = new Submit(pContext,incoming);
                break;
            case cancel:
                break;
            case startMode:
                Log.i("CommandFactory","CreateCommand():startMode");
                acCommand = new StartMode(pContext,incoming);
                break;
            case stopMode:
                break;
            case resetMode:
                break;
            case moveTo:
                Log.i("CommandFactory","CreateCommand():moveTo");
                acCommand = new MoveTo(pContext,incoming);
                break;
            case selectPid:
                break;
            case setMode:
                Log.i("CommandFactory","CreateCommand():setMode("+String.valueOf(value)+")");
                acCommand = new SetMode(pContext, PlatformMode.getMode((int)value),incoming);
                break;
            case setSetpointX:
                Log.i("CommandFactory","CreateCommand():setSetpointX("+String.valueOf(value)+")");
                acCommand = new SetSetpointX(pContext, value, incoming);
                break;
            case setSetpointY:
                Log.i("CommandFactory","CreateCommand():setSetpointY("+String.valueOf(value)+")");
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
                Log.i("CommandFactory","CreateCommand():setIkX("+String.valueOf(value)+")");
                acCommand = new SetIkX(pContext,value, incoming);
                break;
            case setIkY:
                Log.i("CommandFactory","CreateCommand():setIkY("+String.valueOf(value)+")");
                acCommand = new SetIkY(pContext,value, incoming);
                break;
            case setIkZ:
                Log.i("CommandFactory","CreateCommand():setIkZ("+String.valueOf(value)+")");
                acCommand = new SetIkZ(pContext,value, incoming);
                break;
            case setIkRoll:
                Log.i("CommandFactory","CreateCommand():setIkRoll("+String.valueOf(value)+")");
                acCommand = new SetIkRoll(pContext,value, incoming);
                break;
            case setIkPitch:
                Log.i("CommandFactory","CreateCommand():setIkPitch("+String.valueOf(value)+")");
                acCommand = new SetIkPitch(pContext,value, incoming);
                break;
            case setIkYaw:
                Log.i("CommandFactory","CreateCommand():setIkYaw("+String.valueOf(value)+")");
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
                Log.i("CommandFactory","CreateCommand():getFreeHeap("+String.valueOf(value)+")");
                acCommand = new GetFreeHeap(pContext,value,incoming);
                break;
            case getCpuUsage:
                Log.i("CommandFactory","CreateCommand():getCpuUsage("+String.valueOf(value)+")");
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
