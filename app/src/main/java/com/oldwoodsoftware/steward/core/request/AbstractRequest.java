package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.CommandParser;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.core.request.type.RequestType;
import com.oldwoodsoftware.steward.platform.component.StateMachine;
import com.oldwoodsoftware.steward.platform.type.PlatformMode;

import java.util.List;

public abstract class AbstractRequest {
    protected float value;

    public abstract List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate);

    public AbstractRequest(float value){
        this.value = value;
    }

    public static AbstractRequest createRequest(RequestType requestType, float value){
        System.out.println("Debug: createRequest() method called.");
        switch (requestType){
            case setTargetX:
                return new SetTargetXRequest(value);
            case setTargetY:
                return new SetTargetYRequest(value);
            case setX:
                return new SetXRequest(value);
            case setY:
                return new SetYRequest(value);
            case setZ:
                return new SetZRequest(value);
            case setRoll:
                return new SetRollRequest(value);
            case setPitch:
                return new SetPitchRequest(value);
            case setYaw:
                return new SetYawRequest(value);
            default:
                return new NullRequest(value);
        }
    }

    protected static void addIkModeWrapper(List<AbstractCommand> cmds, StateMachine stateMachine, CommandCreator cmdCreate){
        System.out.println("Debug: addIkModeWrapper() method called.");

        if( stateMachine.getMode() != PlatformMode.ikMode){
            cmds.add(cmdCreate.createCommand(CommandType.setMode,(float)PlatformMode.ikMode.get_uC_mode(),false));
            stateMachine.setMode(PlatformMode.ikMode);
        }
        if( stateMachine.isModeStarted() == false){
            cmds.add(cmdCreate.createCommand(CommandType.startMode,0,false));
            stateMachine.setModeStarted(true);
        }
        if( stateMachine.isMoveTo() == false){
            cmds.add(cmdCreate.createCommand(CommandType.moveTo,0,false));
            stateMachine.setMoveTo(true);
        }
    }

    protected  static void addPidModeWrapper(List<AbstractCommand> cmds, StateMachine stateMachine, CommandCreator cmdCreate){
        System.out.println("Debug: addPidModeWrapper() method called.");
        if( stateMachine.getMode() != PlatformMode.pidMode){
            cmds.add(cmdCreate.createCommand(CommandType.setMode,(float)PlatformMode.pidMode.get_uC_mode(),false));
            stateMachine.setMode(PlatformMode.pidMode);
        }
        if( stateMachine.isModeStarted() == false){
            cmds.add(cmdCreate.createCommand(CommandType.startMode,0,false));
            stateMachine.setModeStarted(true);
        }
        if( stateMachine.isMoveTo() == false){
            cmds.add(cmdCreate.createCommand(CommandType.moveTo,0,false));
            stateMachine.setMoveTo(true);
        }
    }
}
