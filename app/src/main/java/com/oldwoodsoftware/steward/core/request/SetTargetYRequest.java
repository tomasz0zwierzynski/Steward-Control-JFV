package com.oldwoodsoftware.steward.core.request;

import com.oldwoodsoftware.steward.core.command.CommandCreator;
import com.oldwoodsoftware.steward.core.command.abstraction.AbstractCommand;
import com.oldwoodsoftware.steward.core.command.type.CommandType;
import com.oldwoodsoftware.steward.platform.component.StateMachine;

import java.util.ArrayList;
import java.util.List;

public class SetTargetYRequest extends AbstractRequest{

    @Override
    public List<AbstractCommand> getCommandsBasedOnStateMachine(StateMachine stateMachine, CommandCreator cmdCreate) {
        System.out.println("Debug: getCommandsBasedOnStateMachine() called on Y");
        List<AbstractCommand> cmds = new ArrayList<>();
        AbstractRequest.addPidModeWrapper(cmds,stateMachine,cmdCreate);
        cmds.add(cmdCreate.createCommand(CommandType.setSetpointY,value,false));
        return cmds;
    }

    public SetTargetYRequest(float value) {
        super(value);
    }

}
