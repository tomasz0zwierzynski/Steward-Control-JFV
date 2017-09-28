package com.oldwoodsoftware.steward.model.bluetooth;

import com.oldwoodsoftware.steward.platform.PlatformContext;
import com.oldwoodsoftware.steward.model.event.DebugFragmentEvents;
import com.oldwoodsoftware.steward.view.fragment.FragmentType;


public class CommandReciever {

    private PlatformContext pContext;

    CommandReciever(PlatformContext context){
        pContext = context;
    }

    void recieveCommand(Command cmd){
        String sCmd = cmd.commandType.toString()+"="+String.valueOf(cmd.value);
        //Put recieved command into debug console temporarly it looks so ugly
        if ((cmd.commandType != CommandType.getFreeHeap) && (cmd.commandType != CommandType.getCpuUsage) ){
            ((DebugFragmentEvents) pContext.getFragmentEventManager().getFragmentEvents(FragmentType.Debug)).printCommandLine(sCmd);
        }

        switch (cmd.commandType){
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
                onBallPositionUpdate(cmd.value,true);
                break;
            case getPanelY:
                onBallPositionUpdate(cmd.value,false);
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
                onServosPositionsUpdate(cmd.value,0);
                break;
            case getIkY:
                onServosPositionsUpdate(cmd.value,1);
                break;
            case getIkZ:
                onServosPositionsUpdate(cmd.value,2);
                break;
            case getIkRoll:
                onServosPositionsUpdate(cmd.value,3);
                break;
            case getIkPitch:
                onServosPositionsUpdate(cmd.value,4);
                break;
            case getIkYaw:
                onServosPositionsUpdate(cmd.value,5);
                break;
            case getFreeHeap:
                pContext.getStatusBar().updateFreeHeap((int)cmd.value);
                break;
            case getCpuUsage:
                pContext.getStatusBar().updateCPUusage(cmd.value);
                break;
            default:
                break;
        }
    }

    void onBallPositionUpdate(float pos, boolean is_X_position){
        pContext.getStatusBar().updateBallStatus(pos,is_X_position);
    }

    void onServosPositionsUpdate(float pos, int index){
        pContext.getStatusBar().updatePlatformStatus(pos,index);
        pContext.getIK().setCurrentXYZABCvalues(pos, index);
    }

}
