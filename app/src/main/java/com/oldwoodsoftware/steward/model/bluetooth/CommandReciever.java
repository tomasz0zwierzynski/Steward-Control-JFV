package com.oldwoodsoftware.steward.model.bluetooth;

import com.oldwoodsoftware.steward.model.PlatformContext;

public class CommandReciever {

    private PlatformContext pContext;

    CommandReciever(PlatformContext context){
        pContext = context;
    }

    void recieveCommand(Command cmd){
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
            default:
                break;
        }


    }

    void onBallPositionUpdate(float pos, boolean is_X_position){
        pContext.getStatusBar().updateBallStatus(pos,is_X_position);
    }

}
